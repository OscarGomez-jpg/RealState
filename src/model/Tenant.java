package model;

public class Tenant {
    private String id;
    private String typeId;
    private String name;
    private String contactNumber;
    private PhoneType phoneType;

    public Tenant(String id, String typeId, String name, String contactNumber, int phoneType) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.phoneType = PhoneType.values()[phoneType];
    }

    public String getId() {
        return id;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public PhoneType getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = PhoneType.values()[phoneType];
    }

}
