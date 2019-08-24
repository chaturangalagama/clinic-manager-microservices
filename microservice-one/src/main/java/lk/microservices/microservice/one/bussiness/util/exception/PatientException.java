package lk.microservices.microservice.one.bussiness.util.exception;

import lk.microservices.microservice.one.technical.enums.StatusCode;

public class PatientException extends CMSException{
    public PatientException(){

    }

    public PatientException(StatusCode code){
        super(code);
    }

    public PatientException(StatusCode code, String message){
        super(code, message);
    }

}