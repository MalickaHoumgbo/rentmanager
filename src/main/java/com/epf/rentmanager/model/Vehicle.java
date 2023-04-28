package com.epf.rentmanager.model;

import java.util.Objects;

public class Vehicle {
    private long identifier;
    private String constructeurs;

    private String modele;

    private Integer nbplaces;

    public Vehicle(long identifier, String constructeurs, String modele, Integer nbplaces) {
        this.identifier = identifier;
        this.constructeurs = constructeurs;
        this.modele = modele;
        this.nbplaces = nbplaces;
    }

    public Vehicle() {
    }



    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public String getConstructeurs() {
        return constructeurs;
    }

    public void setConstructeurs(String constructeurs) {
        this.constructeurs = constructeurs;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public Integer getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(Integer nbplaces) {
        this.nbplaces = nbplaces;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "identifier=" + identifier +
                ", constructeurs='" + constructeurs + '\'' +
                ", modele='"  + modele + '\'' +
                ", nb_places='" + nbplaces + '\'' +

                '}';
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return identifier == vehicle.identifier & constructeurs.equals(vehicle.constructeurs) & modele.equals(vehicle.modele) && nbplaces.equals(vehicle.nbplaces) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, constructeurs, modele, nbplaces);
    }

}
