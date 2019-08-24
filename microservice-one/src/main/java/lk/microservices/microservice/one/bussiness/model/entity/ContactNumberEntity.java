package lk.microservices.microservice.one.bussiness.model.entity;


import lk.microservices.microservice.one.bussiness.util.CommonUtils;

public class ContactNumberEntity {

    private String number;

    public ContactNumberEntity() {
    }

    public ContactNumberEntity(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public String formattedNumber() {
        if (number != null) {
            return number
                    .replace(" ", "")
                    .replace("+", "");
        } else {
            return null;
        }
    }

    public ContactNumberEntity update(String number) {
        this.number = number;
        return this;
    }

    @Override
    public String toString() {
        return "ContactNumber{" +
                ", number='" + number + '\'' +
                '}';
    }

    public boolean areParametersValid() {
        return CommonUtils.isStringValid(number);
    }
}
