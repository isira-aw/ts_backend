package com.tiker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "config")
public class ConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int initialTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private boolean permissionGranted;

    public ConfigEntity() {
    }

    public ConfigEntity(int id, int initialTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, boolean permissionGranted) {
        this.id = id;
        this.initialTickets = initialTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.permissionGranted = permissionGranted;
    }

    // Getters and Setters
    public int getId() { return id; }
    public int getInitialTickets() { return initialTickets; }
    public int getTicketReleaseRate() { return ticketReleaseRate; }
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }
    public int getMaxTicketCapacity() { return maxTicketCapacity; }
    public boolean isPermissionGranted() { return permissionGranted; }

    public void setId(int id) { this.id = id; }
    public void setInitialTickets(int initialTickets) { this.initialTickets = initialTickets; }
    public void setTicketReleaseRate(int ticketReleaseRate) { this.ticketReleaseRate = ticketReleaseRate; }
    public void setCustomerRetrievalRate(int customerRetrievalRate) { this.customerRetrievalRate = customerRetrievalRate; }
    public void setMaxTicketCapacity(int maxTicketCapacity) { this.maxTicketCapacity = maxTicketCapacity; }
    public void setPermissionGranted(boolean permissionGranted) { this.permissionGranted = permissionGranted; }
}
