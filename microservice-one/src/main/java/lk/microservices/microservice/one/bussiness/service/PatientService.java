package lk.microservices.microservice.one.bussiness.service;

import lk.microservices.microservice.one.bussiness.model.dto.PatientDto;
import lk.microservices.microservice.one.technical.exception.RestValidationException;
import lk.microservices.microservice.one.technical.model.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface PatientService {
    ResponseEntity<ApiResponse> createPatient(PatientDto patientDto);

    public ResponseEntity<ApiResponse> search(String type,
                                              String value);


    public ResponseEntity<ApiResponse> validate(String idNumber);

    public ResponseEntity<ApiResponse> likeSearch(String value);

    public ResponseEntity<ApiResponse> update(String id, PatientDto patientDto) throws RestValidationException;

    public ResponseEntity<ApiResponse> register(PatientDto patientDto) throws RestValidationException;


}
