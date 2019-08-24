package lk.microservices.microservice.one.technical.exception;

public class RestValidationException extends RestCheckedException {

    public RestValidationException() {
    }

    public RestValidationException(String message) {
        super(message);
    }
}
