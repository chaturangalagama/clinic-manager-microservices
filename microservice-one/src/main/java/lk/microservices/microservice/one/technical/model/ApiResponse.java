package lk.microservices.microservice.one.technical.model;

import lk.microservices.microservice.one.technical.enums.StatusCode;

public interface ApiResponse {
    String getMessage();

    void setMessage(String message);

    Object getPayload();

    void setPayload(Object payload);

    StatusCode getStatusCode();
}
