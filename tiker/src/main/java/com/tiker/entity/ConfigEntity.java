package com.tiker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;




@Entity
@Table(name = "config_entity")
public class ConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int initialTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    @Column(nullable = false)
    private boolean permissionGranted; // New field

    // Constructors
    public ConfigEntity() {}

    public ConfigEntity(int id, int initialTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, boolean permissionGranted) {
        this.id = id;
        this.initialTickets = initialTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.permissionGranted = permissionGranted;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInitialTickets() {
        return initialTickets;
    }

    public void setInitialTickets(int initialTickets) {
        this.initialTickets = initialTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public boolean isPermissionGranted() {
        return permissionGranted;
    }

    public void setPermissionGranted(boolean permissionGranted) {
        this.permissionGranted = permissionGranted;
    }
}

