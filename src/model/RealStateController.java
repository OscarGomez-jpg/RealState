package model;

import java.util.HashMap;
import java.util.Map;

public class RealStateController {
    private Map<String, Building> buildings;

    public RealStateController() {
        buildings = new HashMap<String, Building>();
    }

    /**
     * This function adds a building in the system
     * 
     * @param name          building's name
     * @param address       building's address
     * @param numApartments the building's number of apartments
     * @return A String validating the operation
     */
    public String addBuilding(String name, String address, int numApartments) {
        String msg = "No se ha podido agregar el edificio";

        if (buildings.containsKey(name) == false) {
            Building nBuilding = new Building(name, address, numApartments);
            buildings.put(name, nBuilding);
            msg = "Edificio agregado exitosamente";
        } else {
            msg = "El edificio ya existe";
        }

        return msg;
    }

    /**
     * This function adds an apartment to a building
     * 
     * @param buildingName building's name
     * @param id           apartment's id
     * @param roomsAmount  apartment's room amount
     * @param bathAmount   apartment's bath amount
     * @param hasBalcony   if the apartment has a balcony
     * @param rent         apartment's rent
     * @return A String validating the operation
     */
    public String addApartmentToBuilding(String buildingName, String id, int roomsAmount, int bathAmount,
            String hasBalcony, double rent) {
        String msg = "No se ha podido agregar el apartamento";
        boolean checker = false;

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se ha encontrado el edificio";

            return msg;
        }

        if (buildings.get(buildingName).searchById(id) != -1) {
            msg = "El edifcio ya existe";

            return msg;
        }

        if (hasBalcony.equalsIgnoreCase("si")) {
            checker = true;
        }

        Apartment newApartment = new Apartment(id, roomsAmount, bathAmount, checker, rent);

        msg = buildings.get(buildingName).addApartment(newApartment);

        return msg;
    }

    /**
     * This function adds a tenant to a building given by the user
     * 
     * @param buildingName  Building's name
     * @param id            Tenant's name
     * @param typeId        Tenant's type id
     * @param name          Tenant's name
     * @param contactNumber Tenant's contact number
     * @param phoneType     Tenant's phone type given as an int
     * @return A String validating the operation
     */
    public String addUserToBuilding(String buildingName, String id, String typeId, String name, String contactNumber,
            int phoneType) {
        String msg = "No se ha podido agregar el arrendatario";

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se ha encontrado el edificio";
            return msg;
        }

        Tenant newTenant = new Tenant(id, typeId, name, contactNumber, phoneType);

        msg = buildings.get(buildingName).addUser(newTenant);

        return msg;
    }

    /**
     * This function adds an Owner of type Tenant to a building
     * 
     * @param buildingName  Building's name
     * @param id            owner's id
     * @param typeId        owner's type id
     * @param name          owner's name
     * @param contactNumber owner's contact number
     * @param phoneType     owner's phone type of PhonType
     * @param numAccount    owner's bank account number
     * @param bankName      owner's bank name
     * @return String validating the operation
     */
    @Overload
    public String addUserToBuilding(String buildingName, String id, String typeId, String name, String contactNumber,
            int phoneType, String numAccount, String bankName) {
        String msg = "No se ha podido agregar el propietario";

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se ha encontrado el edificio";
            return msg;
        }

        Tenant newOner = new Owner(id, typeId, name, contactNumber, phoneType, numAccount, bankName);

        msg = buildings.get(buildingName).addUser(newOner);

        return msg;
    }

    /**
     * This function adds an existing apartment id to an existing owner
     * 
     * @param buildingName The buiding's name
     * @param idApartment  The apartment's id
     * @param idOwner      The owner's id
     * @return A String validating the operation
     */
    public String addApartmentToOwner(String buildingName, String idApartment, String idOwner) {
        String msg = "No se ha podido agregar el apartamento al propietario";

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se encuentra el edificio";
            return msg;
        }

        int apartmentPos = buildings.get(buildingName).searchById(idApartment);
        int ownerPos = buildings.get(buildingName).searchUserById(idOwner);

        if (apartmentPos == -1) {
            msg = "No se encuentra el apartamento";
            return msg;
        }

        if (buildings.get(buildingName).getApartments()[apartmentPos].getHasOwner()) {
            msg = "Este apartamento ya tiene un propietario";
            return msg;
        }

        if (ownerPos == -1) {
            msg = "No se ha encontrado el propietario";
            return msg;
        }

        buildings.get(buildingName).getApartments()[apartmentPos].setHasOwner(true);

        msg = buildings.get(buildingName).getUsers()[ownerPos].addApartment(idApartment);

        return msg;
    }

    /**
     * This function adds an existing Tenant to an existing apartment
     * 
     * @param buildingName building's name
     * @param idApartment  apartment's id
     * @param idTenant     Tenant's id
     * @return A String validation the operation
     */
    public String addApartmentToTenant(String buildingName, String idApartment, String idTenant) {
        String msg = "No se ha podido agregar el apartamento al arrendatario";

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se encuentra el edificio";
            return msg;
        }

        int apartmentPos = buildings.get(buildingName).searchById(idApartment);
        int tenantPos = buildings.get(buildingName).searchUserById(idTenant);

        if (apartmentPos == -1) {
            msg = "No se encuentra el apartamento";
            return msg;
        }

        if (buildings.get(buildingName).getApartments()[apartmentPos].getHasTenant()) {
            msg = "Este apartamento ya tiene un arrendatario";
            return msg;
        }

        if (tenantPos == -1) {
            msg = "No se ha encontrado el arrendatario";
            return msg;
        }

        buildings.get(buildingName).getApartments()[apartmentPos].setHasTenant(true);

        msg = buildings.get(buildingName).getUsers()[tenantPos].addApartment(idApartment);

        return msg;
    }

    /**
     * This function shows the empty apartments of a building given by the user
     * 
     * @param buildingName Building's name
     * @return A String with the empty apartments id and their total
     */
    public String showEmptyApartments(String buildingName) {
        String msg = "";
        int acu = 0;

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se ha encontrado el edificio";
            return msg;
        }

        Building building = buildings.get(buildingName);

        for (int i = 0; i < building.getApartments().length; i++) {
            if (building.getApartments()[i] != null && building.getApartments()[i].getHasTenant() == false) {
                acu += 1;
                msg += "El apartamento " + building.getApartments()[i].getId() + " esta libre\n";
            }
        }

        msg += "Total de apartamentos libres: " + acu;

        return msg;
    }

    /**
     * This function shows the payment of the total rent from a specific building
     * 
     * @param buildingName The building's name given by the user
     * @return A String with the total payment for this building
     */
    public String getFullRentPayment(String buildingName) {
        String msg = "";
        double acu = 0;

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se ha encontrado el edificio";
            return msg;
        }

        Building building = buildings.get(buildingName);

        for (int i = 0; i < building.getApartments().length; i++) {
            if (building.getApartments()[i] != null && building.getApartments()[i].getHasTenant() == true) {
                acu += building.getApartments()[i].getRent();
            }
        }

        msg += "Total de renta a recaudar: " + acu;

        return msg;
    }

    /**
     * This functions shows if an apartment from a building is occupied
     * 
     * @param buildingName The building's name
     * @param idApartment  The apartment's id
     * @return A String validating the operation
     */
    public String getEmptyAparment(String buildingName, String idApartment) {
        String msg = "";

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se ha encontrado el edificio";
            return msg;
        }

        int apartmentPos = buildings.get(buildingName).searchById(idApartment);

        if (apartmentPos == -1) {
            msg = "No se ha encontrado el apartamento";
            return msg;
        }

        if (buildings.get(buildingName).getApartments()[apartmentPos].getHasTenant()) {
            msg = "El apartamento ya esta ocupado";
        } else {
            msg = "El apartamento esta libre";
        }

        return msg;
    }

    /**
     * This function shows how many apartments owns an owner
     * 
     * @param buildingName building's name
     * @param ownerId      owner's id
     * @return A String validating the operation
     */
    public String getOwnerTotalApartments(String buildingName, String ownerId) {
        String msg = "";

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se ha encontrado el edificio";
            return msg;
        }

        int ownerPos = buildings.get(buildingName).searchUserById(ownerId);

        if (ownerPos == -1) {
            msg = "No se ha encontrado el propietario";
            return msg;
        }

        msg = "La cantidad de apartamentos del propietario son: "
                + buildings.get(buildingName).getUsers()[ownerPos].getApartments().size();

        return msg;
    }

    public String getOwnerPayment(String buildingName, String ownerId) {
        String msg = "";
        double total = 0;

        if (buildings.containsKey(buildingName) == false) {
            msg = "No se ha encontrado el edificio";
            return msg;
        }

        int ownerPos = buildings.get(buildingName).searchUserById(ownerId);

        if (ownerPos == -1) {
            msg = "No se ha encontrado el propietario";
            return msg;
        }

        Building building = buildings.get(buildingName);

        for (String rent : building.getUsers()[ownerPos].getApartments()) {
            int apartmentPos = building.searchById(rent);
            total += building.getApartments()[apartmentPos].getRent();
        }

        msg = "La cantidad de renta de los apartamentos del propietario en total es " + total + "\n" +
                " El pago correspondiente es de: " + (total * 0.9);

        return msg;
    }
}
