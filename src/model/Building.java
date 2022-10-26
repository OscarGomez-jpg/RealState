package model;

public class Building {
    public static final int TOTAL_USERS = 20;
    
    private String name;
    private String address;
    private int numApartments;
    private Apartment [] apartments;
    private Tenant [] users;
    
    public Building(String name, String address, int numApartments) {
        this.name = name;
        this.address = address;
        this.numApartments = numApartments;
        this.apartments = new Apartment[this.numApartments];
        this.users = new Tenant[TOTAL_USERS];
    }

    /**
     * This function adds an apartment to the building
     * @param apartment the apartment that is going to be added
     * @return A String validating the operation
     */
    public String addApartment(Apartment apartment) {
        String msg = "El edificio esta lleno";
        boolean isEmpty = false;

        for (int i = 0; i < numApartments && isEmpty == false; i++) {
            if (apartments[i] == null) {
                apartments[i] = apartment;
                isEmpty = true;
                msg = "Apartamento agregado con exito";
            }
        }

        return msg;
    }

    /**
     * This function adds an owner of type tenant to a building
     * @param owner Owner of type tenant
     * @return A String validating the operation
     */
    public String addUser(Tenant owner) {
        String msg = "El edficio esta lleno";
        boolean isEmpty = false;

        for (int i = 0; i < TOTAL_USERS && isEmpty == false; i++) {
            if (users[i] == null) {
                users[i] = owner;
                isEmpty = true;
                msg = "Usuario agregado con exito";
            }
        }

        return msg;
    }

    /**
     * This function returns the position of an apartment by its id
     * @param apartmentId apartment's id
     * @return the position of the apartment
     */
    public int searchById(String apartmentId) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < apartments.length && isFound == false; i++) {
            if (apartments[i] != null && apartments[i].getId().equals(apartmentId)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    /**
     * This function searches an ownerby his id
     * @param userId the user id given by the user
     * @return the position of the user
     */
    public int searchUserById(String userId) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < users.length && isFound == false; i++) {
            if (users[i] != null && users[i].getId().equals(userId)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
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

    public Tenant[] getUsers() {
        return users;
    }

    public Apartment[] getApartments() {
        return apartments;
    }
    
    
}
