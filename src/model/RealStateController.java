package model;

import java.util.HashMap;
import java.util.Map;

public class RealStateController {
    private Map<String, Building> buildings;

    public RealStateController() {
        buildings = new HashMap<String, Building>();
    }

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

    public String addApartmentToBuilding(String buildingName, String id, int roomsAmount, int bathAmount,
            String hasBalcony, double rent) {
        String msg = "No se ha podido agregar el apartamento";
        boolean checker = false;

        if (buildings.containsKey(buildingName) == false) {
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
}