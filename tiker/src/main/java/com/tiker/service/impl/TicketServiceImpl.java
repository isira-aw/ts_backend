package com.tiker.service.impl;

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
        stopSimulation(); // Stop existing simulation if any

        // Retrieve or create a configuration for the simulation
        ConfigEntity config = retrieveOrCreateConfig(startRequestDto);

        int initialTickets = config.getInitialTickets();
        int ticketReleaseRate = config.getTicketReleaseRate();
        int customerRetrievalRate = config.getCustomerRetrievalRate();
        int maxTicketCapacity = config.getMaxTicketCapacity();

        // Validate the rates
        if (ticketReleaseRate <= 0 || customerRetrievalRate <= 0) {
            throw new IllegalArgumentException("Ticket release rate and customer retrieval rate must be greater than 0.");
        }

        // Initialize ticket pool
        ticketPool = new TicketPool(initialTickets, maxTicketCapacity);

        Vendor.TicketUpdateCallback callback = (message, currentCount) -> {
            TicketEventDto event = new TicketEventDto(message, currentCount);
            messagingTemplate.convertAndSend("/topic/ticketUpdates", event);
            System.out.println(message); // Also print to console for verification
        };

        // Create vendor threads
        vendorThreads = createThreads(3, i -> new Vendor(i + 1, ticketPool, ticketReleaseRate, callback));
        // Create customer threads
        customerThreads = createThreads(3, i -> new Customer(i + 1, ticketPool, customerRetrievalRate, callback));
    }

    @Override
    public void stopSimulation() {
        stopThreads(vendorThreads);
        stopThreads(customerThreads);
        ticketPool = null;
    }

    private ConfigEntity retrieveOrCreateConfig(StartRequestDto dto) {
        if (dto.getConfigId() > 0) {
            ConfigEntity config = configService.getConfigById(dto.getConfigId());
            if (config == null) {
                throw new IllegalArgumentException("Configuration with ID " + dto.getConfigId() + " not found.");
            }
            return config;
        } else {
            if (dto.getInitialTickets() > 0 && dto.getTicketReleaseRate() > 0 &&
                    dto.getCustomerRetrievalRate() > 0 && dto.getMaxTicketCapacity() > 0) {

                NewConfigRequestDto newConfig = new NewConfigRequestDto(
                        dto.getInitialTickets(),
                        dto.getTicketReleaseRate(),
                        dto.getCustomerRetrievalRate(),
                        dto.getMaxTicketCapacity(),
                        true
                );
                return configService.createConfig(newConfig);
            } else {
                throw new IllegalArgumentException("Invalid configuration values provided.");
            }
        }
    }

    private Thread[] createThreads(int count, java.util.function.Function<Integer, Runnable> taskSupplier) {
        Thread[] threads = new Thread[count];
        for (int i = 0; i < count; i++) {
            threads[i] = new Thread(taskSupplier.apply(i), "Thread-" + (i + 1));
            threads[i].start();
        }
        return threads;
    }

    private void stopThreads(Thread[] threads) {
        if (threads != null) {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }
    }
}
