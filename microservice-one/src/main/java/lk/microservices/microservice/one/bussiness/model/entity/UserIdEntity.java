package lk.microservices.microservice.one.bussiness.model.entity;

import lk.microservices.microservice.one.bussiness.util.CommonUtils;

import java.util.Objects;

public class UserIdEntity {

    public enum IdType {
        NRIC_PINK, NRIC_BLUE, NRIC, FIN, MIC, PASSPORT, OTHER
    }

    private IdType idType;
    private String number;

    public UserIdEntity() {
    }

    public UserIdEntity(IdType idType, String number) {
        this.idType = idType;
        this.number = number;
    }

    public UserIdEntity update(IdType idType, String number) {
        this.idType = idType;
        this.number = number;
        return this;
    }

    public boolean areParametersValid() {
        return idType != null && CommonUtils.isStringValid(number);
    }

    public IdType getIdType() {
        return idType;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIdEntity userIdEntity = (UserIdEntity) o;
        return idType == userIdEntity.idType &&
                Objects.equals(number, userIdEntity.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idType, number);
    }

    @Override
    public String toString() {
        return "UserId{" +
                "idType=" + idType +
                ", number='" + number + '\'' +
                '}';
    }
}
