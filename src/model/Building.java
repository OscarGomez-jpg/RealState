package model;

public class Building {
    private String name;
    private String address;
    private int numApartments;
    private Apartment [] apartments;

    public Building(String name, String address, int numApartments) {
        this.name = name;
        this.address = address;
        this.numApartments = numApartments;
        this.apartments = new Apartment[this.numApartments];
    }

    public String addApartment(Apartment apartment) {
        String msg = "El edificio esta lleno";
        boolean isDone = false;

        for (int i = 0; i < numApartments && isDone == false; i++) {
            if (apartments[i] == null) {
                apartments[i] = apartment;
                isDone = true;
                msg = "Apartamento agregado con exito";
            }
        }

        return msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumApartments() {
        return numApartments;
    }

    public void setNumApartments(int numApartments) {
        this.numApartments = numApartments;
    }
    
}
