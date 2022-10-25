package model;

public class Owner extends Tenant {
    private String numAccount;
    private String bankName;

    public Owner(String id, String typeId, String name, String contactNumber, int phoneType, String numAccount, String bankName) {
        super(id, typeId, name, contactNumber, phoneType);
        this.numAccount = numAccount;
        this.bankName = bankName;
    }

    @Override
    public String addApartment(String apartmentId) {
        String msg = "Apartamento agregado con exito";

        if (getApartments().contains(apartmentId)) {
            msg = "Este propietario ya posee este apartamento";
            
            return msg;
        }

        getApartments().add(apartmentId);

        return msg;
    }

    public String getNumAccount() {
        return numAccount;
    }

    public void setNumAccount(String numAccount) {
        this.numAccount = numAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }    
}
