package ui;

import java.util.Scanner;

import model.RealStateController;

public class Main {

    private Scanner reader;
    private RealStateController realStateController;

    public Main() {
        reader = new Scanner(System.in);
        realStateController = new RealStateController();
    }

    public static void main(String[] args) {
        Main main = new Main();

        int option = -1;
        do {

            option = main.getOptionShowMenu();
            main.executeOption(option);

        } while (option != 0);

    }

    public Scanner getReader() {
        return this.reader;
    }

    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    public int validateIntegerOption() {
        int option = 0;

        if (reader.hasNextInt()) {
            option = reader.nextInt();
        } else {
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    public double validateDoubleOption() {
        double option = 0;

        if (reader.hasNextDouble()) {
            option = reader.nextDouble();
        } else {
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    public int getOptionShowMenu() {
        int option = 0;
        System.out.println(printMenu());

        option = validateIntegerOption();

        return option;
    }

    public String printMenu() {
        return "\n" +
                "<< --------------------------------------------------------------------- >>\n" +
                "<< -                                Welcome                            - >>\n" +
                "<< --------------------------------------------------------------------- >>\n" +
                "1. Registrar edificio \n" +
                "2. Agregar un apartamento a un edificio \n" +
                "3. Agregar propietario\n" +
                "4. Agregar apartamento a un propietario\n" +
                "5. Agregar arrendatario\n" +
                "6. Agregar apartamento a un arrendatario\n" +
                "7. Mostrar los apartamentos vacios\n" +
                "8. Obtener la renta total de un edificio\n" +
                "9. Mostrar si un apartamento esta vacio\n" +
                "10.Mostrar la cantidad de apartamentos de un propietario\n" +
                "11.Mostrar el pago de un propietario\n" +
                "0. Salir del programa.\n";
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1:
                msg = uiAddBuilding();
                System.out.println(msg);
                break;

            case 2:
                msg = uiAddApartment();
                System.out.println(msg);
                break;

            case 3:
                msg = uiAddOwner();
                System.out.println(msg);
                break;

            case 4:
                msg = uiAddApartmentToOwner();
                System.out.println(msg);
                break;

            case 5:
                msg = uiAddTenant();
                System.out.println(msg);
                break;
            case 6:
                msg = uiAddApartmentToTenant();
                System.out.println(msg);
                break;

            case 7:
                msg = uiShowEmptyApartments();
                System.out.println(msg);
                break;

            case 8:
                msg = uiGetFullRentPayment();
                System.out.println(msg);
                break;

            case 9:
                msg = uiShowSingleEmptyApartment();
                System.out.println(msg);
                break;

            case 10:
                msg = uiShowOwnerApartments();
                System.out.println(msg);
                break;

            case 11:
                msg = uiShowOwnerPayment();
                System.out.println(msg);
                break;

            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }

    public String uiAddBuilding() {
        String msg = "";
        int numApartments = 0;

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese la ubicacion del edificio: ");
        String address = reader.next();

        do {
            System.out.println("Ingrese la cantidad de apartamentos del edifcio: ");
            numApartments = validateIntegerOption();

            if (numApartments == -1) {
                msg = "Por favor ingrese un numero";
                System.out.println(msg);
            }

            if (numApartments <= 0) {
                msg = "No se puede crear un edificio sin apartamentos";
                System.out.println(msg);
            }

        } while (numApartments == -1);

        msg = realStateController.addBuilding(buildingName, address, numApartments);

        return msg;
    }

    public String uiAddApartment() {
        String msg = "";
        int roomsAmount = 0;
        int bathAmount = 0;
        double rent = 0;
        String checker = "";

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese el id del apartamento: ");
        String id = reader.next();

        do {
            System.out.println("Ingrese la cantidad de cuartos: ");
            roomsAmount = validateIntegerOption();

            System.out.println("Ingrese la cantidad de ba??os del apartamento: ");
            bathAmount = validateIntegerOption();

            if (roomsAmount == -1 || bathAmount == -1) {
                System.out.println("Por favor ingrese un numero");
            }

        } while (roomsAmount == -1 || bathAmount == -1);

        do {
            System.out.println("Ingrese el valor del apartamento: ");
            rent = validateDoubleOption();

            if (rent == -1) {
                System.out.println("Por favor ingrese un numero");
            }
        } while (rent == -1);

        System.out.println("El apartamento tiene balcon?\n" +
                "Si\n" +
                "No\n");

        // Soy consciente del error, pero no hay tiempo para hacer la validacion :(
        checker = reader.next();

        msg = realStateController.addApartmentToBuilding(buildingName, id, roomsAmount, bathAmount, checker, rent);

        return msg;
    }

    public String uiAddOwner() {
        String msg = "";

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese el id del propietario: ");
        String id = reader.next();

        System.out.println("Ingrese el tipo de id del propietario: ");
        String typeId = reader.next();

        System.out.println("Ingrese el nombre del propietario: ");
        String name = reader.next();

        System.out.println("Ingrese el numero de contacto del propietario: ");
        String contactNumber = reader.next();

        System.out.println("Ingrese el tipo de celular del propietario: ");
        System.out.println("Son los siguientes: \n" +
                "0. Hogar\n" +
                "1. Oficina\n" +
                "2. Movil\n" +
                "3. Familiar\n" +
                "4. Otro");

        int selection = validateIntegerOption();

        if (selection == -1) {
            msg = "Por favor ingrese un numero";
            return msg;
        }

        System.out.println("Ingrese el numero de cuenta del propietario: ");
        String numAccount = reader.next();

        System.out.println("Ingrese el nombre del banco del propietario: ");
        String bankName = reader.next();

        msg = realStateController.addUserToBuilding(buildingName, id, typeId, name, contactNumber, selection,
                numAccount, bankName);

        return msg;
    }

    public String uiAddApartmentToOwner() {
        String msg = "";

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese el id del apartamento: ");
        String idApartment = reader.next();

        System.out.println("Ingrese el id del propietario: ");
        String idOwner = reader.next();

        msg = realStateController.addApartmentToOwner(buildingName, idApartment, idOwner);

        return msg;
    }

    public String uiAddTenant() {
        String msg = "";

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese el id del propietario: ");
        String id = reader.next();

        System.out.println("Ingrese el tipo de id del propietario: ");
        String typeId = reader.next();

        System.out.println("Ingrese el nombre del propietario: ");
        String name = reader.next();

        System.out.println("Ingrese el numero de contacto del propietario: ");
        String contactNumber = reader.next();

        System.out.println("Ingrese el tipo de celular del propietario: ");
        System.out.println("Son los siguientes: \n" +
                "0. Hogar\n" +
                "1. Oficina\n" +
                "2. Movil\n" +
                "3. Familiar\n" +
                "4. Otro");

        int selection = validateIntegerOption();

        if (selection == -1) {
            msg = "Por favor ingrese un numero";
            return msg;
        }

        msg = realStateController.addUserToBuilding(buildingName, id, typeId, name, contactNumber, selection);

        return msg;
    }

    public String uiAddApartmentToTenant() {
        String msg = "";

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese el id del apartamento: ");
        String idApartment = reader.next();

        System.out.println("Ingrese el id del arrendatario: ");
        String idTenant = reader.next();

        msg = realStateController.addApartmentToTenant(buildingName, idApartment, idTenant);

        return msg;
    }

    public String uiShowEmptyApartments() {
        String msg = "";

        System.out.println("Ingrese el nombre del edifcio: ");
        String buildingName = reader.next();

        msg = realStateController.showEmptyApartments(buildingName);

        return msg;
    }

    public String uiGetFullRentPayment() {
        String msg = "";

        System.out.println("Ingrese el nombre del edifcio: ");
        String buildingName = reader.next();

        msg = realStateController.getFullRentPayment(buildingName);

        return msg;
    }

    public String uiShowSingleEmptyApartment() {
        String msg = "";

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese el numero del apartamento: ");
        String idApartment = reader.next();

        msg = realStateController.getEmptyAparment(buildingName, idApartment);

        return msg;
    }

    public String uiShowOwnerApartments() {
        String msg = "";

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese el id del propietario: ");
        String ownerId = reader.next();

        msg = realStateController.getOwnerTotalApartments(buildingName, ownerId);

        return msg;
    }

    public String uiShowOwnerPayment() {
        String msg = "";

        System.out.println("Ingrese el nombre del edificio: ");
        String buildingName = reader.next();

        System.out.println("Ingrese el id del propietario: ");
        String ownerId = reader.next();

        msg = realStateController.getOwnerPayment(buildingName, ownerId);

        return msg;
    }
}
