package com.epf.rentmanager.model;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
   private long identifier;
   private String name;

   private String lastName;

   private String emailAdress;
   private LocalDate birthdate;



    public Client(long identifier, String name, String lastName, String emailAdress, LocalDate birthdate) {
        this.identifier = identifier;
        this.name = name;
        this.lastName = lastName;
        this.emailAdress = emailAdress;
        this.birthdate = birthdate;
    }

    public Client() {
    }

    public long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(long identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return "Client{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAdress='" + emailAdress + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return identifier == client.identifier && name.equals(client.name) && lastName.equals(client.lastName) && emailAdress.equals(client.emailAdress) && birthdate.equals(client.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, name, lastName, emailAdress, birthdate);
    }


}
