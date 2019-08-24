package lk.microservices.microservice.two.technical.model;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class AuthResponse<T> {

    private T data;
    private int status;
    private String statusMessage;
    private Map<String,Object> additionalParams;

    public AuthResponse(T data, HttpStatus httpStatus) {
        this.data = data;
        this.status = httpStatus.value();
        this.statusMessage = httpStatus.getReasonPhrase();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map<String, Object> getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(Map<String, Object> additionalParams) {
        this.additionalParams = additionalParams;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "data=" + data +
                ", status=" + status +
                ", statusMessage='" + statusMessage + '\'' +
                ", additionalParams=" + additionalParams +
                '}';
    }

}
