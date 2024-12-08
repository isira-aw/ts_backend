package com.tiker.service.impl;

import com.tiker.dto.RequestConfigDto;
import com.tiker.dto.TicketEventDto;
import com.tiker.model.Customer;
import com.tiker.model.TicketPool;
import com.tiker.model.Vendor;
import com.tiker.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Service that handles starting and stopping the simulation.
 * Uses threads as in the CLI example.
 */
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private Thread[] vendorThreads;
    private Thread[] customerThreads;
    private TicketPool ticketPool;

    @Override
    public void startSimulation(RequestConfigDto configDto) {
        if (vendorThreads != null || customerThreads != null) {
            // Already running, you may want to handle this case
            return;
        }

        ticketPool = new TicketPool(configDto.getInitialTickets(), configDto.getMaxTicketCapacity());

        Vendor.TicketUpdateCallback callback = new Vendor.TicketUpdateCallback() {
            @Override
            public void onTicketUpdate(String message, int currentCount) {
                // Send message through WebSocket
                TicketEventDto event = new TicketEventDto(message, currentCount);
                messagingTemplate.convertAndSend("/topic/ticketUpdates", event);
            }
        };

        vendorThreads = new Thread[3];
        customerThreads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            vendorThreads[i] = new Thread(new Vendor(i + 1, ticketPool, configDto.getTicketReleaseRate(), callback), "Vendor-" + (i + 1));
            customerThreads[i] = new Thread(new Customer(i + 1, ticketPool, configDto.getCustomerRetrievalRate(), callback), "Customer-" + (i + 1));
            vendorThreads[i].start();
            customerThreads[i].start();
        }
    }

    @Override
    public void stopSimulation() {
        if (vendorThreads == null || customerThreads == null) return;

        for (Thread t : vendorThreads) {
            t.interrupt();
        }
        for (Thread t : customerThreads) {
            t.interrupt();
        }

        vendorThreads = null;
        customerThreads = null;
        ticketPool = null;
    }
}
