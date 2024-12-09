package com.tiker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
//
//@NoArgsConstructor // Add this to generate the no-argument constructor
public class ConfigEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int initialTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    private boolean permissionGranted;

    public Long getId() {
        return id;
    }
    public ConfigEntity() {
    }


    public void setId(Long id) {
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

    public ConfigEntity(Long id, int initialTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, boolean permissionGranted) {
        this.id = id;
        this.initialTickets = initialTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.permissionGranted = permissionGranted;
    }
}
