package com.tiker.service.impl;

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

    // These are not beans, just runtime objects
    private Thread[] vendorThreads;
    private Thread[] customerThreads;
    private TicketPool ticketPool;

    // Use constructor injection for real beans
    public TicketServiceImpl(SimpMessagingTemplate messagingTemplate, ConfigService configService) {
        this.messagingTemplate = messagingTemplate;
        this.configService = configService;
    }

    @Override
    public void startSimulation(Long configId) {
        stopSimulation(); // stop if already running

        ConfigEntity config = configService.getConfigById(configId);

        if (!config.isPermissionGranted()) {
            throw new RuntimeException("Permission not granted for config ID: " + configId);
        }

        // Create new TicketPool and threads here, not autowired
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
}
