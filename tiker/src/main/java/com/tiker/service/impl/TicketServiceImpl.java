package com.tiker.service.impl;

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

    private  SimpMessagingTemplate messagingTemplate;
    private final ConfigService configService;

    private Thread[] vendorThreads;
    private Thread[] customerThreads;
    private TicketPool ticketPool;

    public TicketServiceImpl(SimpMessagingTemplate messagingTemplate, ConfigService configService) {
        this.messagingTemplate = messagingTemplate;
        this.configService = configService;
    }

    public SimpMessagingTemplate getMessagingTemplate() {
        return messagingTemplate;
    }

    public ConfigService getConfigService() {
        return configService;
    }

    public Thread[] getVendorThreads() {
        return vendorThreads;
    }

    public void setVendorThreads(Thread[] vendorThreads) {
        this.vendorThreads = vendorThreads;
    }

    public Thread[] getCustomerThreads() {
        return customerThreads;
    }

    public void setCustomerThreads(Thread[] customerThreads) {
        this.customerThreads = customerThreads;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }


    @Override
    public void startSimulation(StartRequestDto startRequestDto) {
        stopSimulation(); // stop if already running

        // Try to get config by ID
        ConfigEntity config = configService.getConfigById(startRequestDto.getConfigId());

        if (config == null) {
            // If configId not found, create a new config from the request fields
            config = configService.createConfigFromStart(
                    startRequestDto.getInitialTickets(),
                    startRequestDto.getTicketReleaseRate(),
                    startRequestDto.getCustomerRetrievalRate(),
                    startRequestDto.getMaxTicketCapacity()
            );
        }

        // Create new TicketPool and threads
        ticketPool = new TicketPool(config.getInitialTickets(), config.getMaxTicketCapacity());

        Vendor.TicketUpdateCallback callback = (message, currentCount) -> {
            TicketEventDto event = new TicketEventDto(message, currentCount);
            messagingTemplate.convertAndSend("/topic/ticketUpdates", event);
        };

        vendorThreads = new Thread[3];
        customerThreads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            vendorThreads[i] = new Thread(new Vendor(i + 1, ticketPool, config.getTicketReleaseRate(), callback), "Vendor-" + (i + 1));
            customerThreads[i] = new Thread(new Customer(i + 1, ticketPool, config.getCustomerRetrievalRate(), callback), "Customer-" + (i + 1));
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

    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
}
