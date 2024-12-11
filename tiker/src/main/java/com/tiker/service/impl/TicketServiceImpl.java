package com.tiker.service.impl;

import com.tiker.dto.ConfigDto;
import com.tiker.dto.NewConfigRequestDto;
import com.tiker.dto.StartRequestDto;
import com.tiker.dto.TicketEventDto;
import com.tiker.entity.ConfigEntity;
import com.tiker.model.Customer;
import com.tiker.model.TicketPool;
import com.tiker.model.Vendor;
import com.tiker.service.ConfigService;
import com.tiker.service.TicketService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final SimpMessagingTemplate messagingTemplate;
    private final ConfigService configService;

    private Thread[] vendorThreads;
    private Thread[] customerThreads;
    private TicketPool ticketPool;

    public TicketServiceImpl(SimpMessagingTemplate messagingTemplate, ConfigService configService) {
        this.messagingTemplate = messagingTemplate;
        this.configService = configService;
    }

    @Override
    public void startSimulation(StartRequestDto startRequestDto) {
        stopSimulation(); // Stop existing simulation

        ConfigEntity config;
        if (startRequestDto.getConfigId() > 0) {
            // Fetch configuration by ID
            config = configService.getConfigById(startRequestDto.getConfigId());
            if (config == null) {
                throw new IllegalArgumentException("Configuration with ID " + startRequestDto.getConfigId() + " not found.");
            }
        } else {
            // Check if input values are provided
            if (startRequestDto.getInitialTickets() > 0 && startRequestDto.getTicketReleaseRate() > 0 &&
                    startRequestDto.getCustomerRetrievalRate() > 0 && startRequestDto.getMaxTicketCapacity() > 0) {

                // Save new configuration
                NewConfigRequestDto newConfig = new NewConfigRequestDto(
                        startRequestDto.getInitialTickets(),
                        startRequestDto.getTicketReleaseRate(),
                        startRequestDto.getCustomerRetrievalRate(),
                        startRequestDto.getMaxTicketCapacity()
                );
                config = configService.createConfig(newConfig);

            } else {
                // Generate next available configuration ID
                List<ConfigDto> allConfigs = configService.getAllConfigs();
                if (allConfigs.isEmpty()) {
                    throw new IllegalArgumentException("No configurations available in the database.");
                }
                int nextId = allConfigs.get(allConfigs.size() - 1).getId() + 1;
                config = configService.getConfigById(nextId);
                if (config == null) {
                    throw new IllegalArgumentException("Configuration with ID " + nextId + " not found.");
                }
            }
        }

        // Extract values for simulation
        int initialTickets = config.getInitialTickets();
        int ticketReleaseRate = config.getTicketReleaseRate();
        int customerRetrievalRate = config.getCustomerRetrievalRate();
        int maxTicketCapacity = config.getMaxTicketCapacity();

        ticketPool = new TicketPool(initialTickets, maxTicketCapacity);

        Vendor.TicketUpdateCallback callback = (message, currentCount) -> {
            TicketEventDto event = new TicketEventDto(message, currentCount);
            messagingTemplate.convertAndSend("/topic/ticketUpdates", event);
        };

        vendorThreads = new Thread[3];
        customerThreads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            vendorThreads[i] = new Thread(new Vendor(i + 1, ticketPool, ticketReleaseRate, callback), "Vendor-" + (i + 1));
            customerThreads[i] = new Thread(new Customer(i + 1, ticketPool, customerRetrievalRate, callback), "Customer-" + (i + 1));
            vendorThreads[i].start();
            customerThreads[i].start();
        }
    }

    @Override
    public void stopSimulation() {
        if (vendorThreads != null) {
            for (Thread t : vendorThreads) {
                t.interrupt();
            }
            vendorThreads = null;
        }
        if (customerThreads != null) {
            for (Thread t : customerThreads) {
                t.interrupt();
            }
            customerThreads = null;
        }
        ticketPool = null;
    }
}
