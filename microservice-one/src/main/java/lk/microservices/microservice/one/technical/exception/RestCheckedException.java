package lk.microservices.microservice.one.technical.exception;

public class RestCheckedException extends Exception {

    public RestCheckedException() {
    }

    public RestCheckedException(String message) {
        super(message);
    }
}
