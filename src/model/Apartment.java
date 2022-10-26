package model;

public class Apartment {
    private String id;
    private int roomsAmount;
    private int bathAmount;
    private boolean hasBalcony;
    private boolean hasOwner;
    private boolean hasTenant;
    private double rent;
    
    public Apartment(String id, int roomsAmount, int bathAmount, boolean hasBalcony, double rent) {
        this.id = id;
        this.roomsAmount = roomsAmount;
        this.bathAmount = bathAmount;
        this.hasBalcony = hasBalcony;
        this.rent = rent;
        this.hasTenant = false;
        this.hasOwner = false;
    }

    public String getId() {
        return id;
    }

    public int getRoomsAmount() {
        return roomsAmount;
    }

    public void setRoomsAmount(int roomsAmount) {
        this.roomsAmount = roomsAmount;
    }

    public int getBathAmount() {
        return bathAmount;
    }

    public void setBathAmount(int bathAmount) {
        this.bathAmount = bathAmount;
    }

    public boolean getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public double getRent() {
        return rent;
    }

    public void setRent(double rent) {
        this.rent = rent;
    }

    public boolean getHasOwner() {
        return hasOwner;
    }

    public boolean getHasTenant() {
        return hasTenant;
    }

    public void setHasOwner(boolean hasOwner) {
        this.hasOwner = hasOwner;
    }

    public void setHasTenant(boolean hasTenant) {
        this.hasTenant = hasTenant;
    }
}
