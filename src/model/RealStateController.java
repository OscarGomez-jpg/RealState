package model;

import java.util.HashMap;
import java.util.Map;

public class RealStateController {
    private Map<String, Building> buildings;

    public RealStateController() {
        buildings = new HashMap<String, Building>();
    }

    /**
     * This function let you validate if a building is registered in the system
     * @param buildingName building name
     * @return true if the building exists
     */
    public boolean validateBuildingName(String buildingName) {
        boolean confirmation = false;

        if (buildings.containsKey(buildingName)) {
            confirmation = true;
        }

        return confirmation;
    }

    /**
     * This function adds a building in the system
     * @param name building's name
     * @param address building's address
     * @param numApartments the building's number of apartments
     * @return A String validating the operation
     */
    public String addBuilding(String name, String address, int numApartments) {
        String msg = "No se ha podido agregar el edificio";

        if (validateBuildingName(name) == false) {
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
     * @param buildingName building's name
     * @param id apartment's id
     * @param roomsAmount apartment's room amount
     * @param bathAmount apartment's bath amount
     * @param hasBalcony if the apartment has a balcony
     * @param rent apartment's rent
     * @return A String validating the operation
     */
    public String addApartmentToBuilding(String buildingName, String id, int roomsAmount, int bathAmount,
            String hasBalcony, double rent) {
        String msg = "No se ha podido agregar el apartamento";
        boolean checker = false;

        if (validateBuildingName(buildingName) == false) {
            msg = "No se ha encontrado el edificio";

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
     * This function adds an Owner of type Tenant to a building
     * @param buildingName Building's name
     * @param id owner's id
     * @param typeId owner's type id
     * @param name owner's name
     * @param contactNumber owner's contact number
     * @param phoneType owner's phone type of PhonType
     * @param numAccount owner's bank account number
     * @param bankName owner's bank name
     * @return String validating the operation
     */
    public String addOwnerToBuilding(String buildingName, String id, String typeId, String name, String contactNumber,
            int phoneType, String numAccount, String bankName) {
        String msg = "No se ha podido agregar el propietario";
        
        if (validateBuildingName(buildingName) == false) {
            msg = "No se ha encontrado el edificio";
            return msg;
        }
        
        Tenant newOner = new Owner(id, typeId, name, contactNumber, phoneType, numAccount, bankName);

        msg = buildings.get(buildingName).addOwner(newOner);

        return msg;
    }

    public String addApartmentToOwner(String buildingName, String idApartment, String idOwner) {
        String msg = "No se ha podido agregar el apartamento al propietario";
        int ownerPos = -1;

        if (validateBuildingName(buildingName) == false) {
            msg = "No se encuentra el edificio";
            return msg;
        }

        if (buildings.get(buildingName).searchById(idApartment) == -1) {
            msg = "No se encuentra el apartamento";
            return msg;
        }

        ownerPos = buildings.get(buildingName).searchOwnerById(idOwner);

        if (ownerPos == -1) {
            msg = "No se ha encontrado el propietario";
            return msg;
        }

        msg = buildings.get(buildingName).getUsers()[ownerPos].addApartment(idApartment);

        return msg;
    }
}