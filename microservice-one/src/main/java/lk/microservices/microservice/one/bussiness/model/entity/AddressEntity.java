package lk.microservices.microservice.one.bussiness.model.entity;


import lk.microservices.microservice.one.bussiness.util.CommonUtils;

public class AddressEntity {
    private String address;
    private String country;
    private String postalCode;

    public AddressEntity(String address, String country, String postalCode) {
        this.address = address;
        this.country = country;
        this.postalCode = postalCode;
    }


    public AddressEntity() {
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public boolean areParametersValid() {
        return CommonUtils.isStringValid(address) && CommonUtils.isStringValid(country);
    }
}
