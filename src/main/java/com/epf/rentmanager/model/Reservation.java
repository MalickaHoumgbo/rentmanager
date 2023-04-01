package com.epf.rentmanager.model;

import java.time.LocalDate;

public class Reservation {
    private long identifier;
    private static long  client_id;

    private long vehicle_id;
    private LocalDate debut;
    private  LocalDate fin;

    public Reservation(long identifier, long client_id, long vehicle_id, LocalDate debut, LocalDate fin) {
        this.identifier = identifier;
        this.client_id = client_id;
        this.vehicle_id = vehicle_id;
        this.debut = debut;
        this.fin = fin;
    }

    public Reservation() {

    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public static long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public long getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(long vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "identifier=" + identifier +
                ", client_id=" + client_id +
                ", vehicle_id=" + vehicle_id +
                ", debut=" + debut +
                ", fin=" + fin +
                '}';
    }
}
