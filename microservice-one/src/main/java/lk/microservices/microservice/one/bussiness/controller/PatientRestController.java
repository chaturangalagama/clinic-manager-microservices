package lk.microservices.microservice.one.bussiness.controller;

import lk.microservices.microservice.one.bussiness.model.dto.PatientDto;
import lk.microservices.microservice.one.bussiness.service.PatientService;
import lk.microservices.microservice.one.technical.exception.RestValidationException;
import lk.microservices.microservice.one.technical.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin
@RestController
@RequestMapping("/patient")
//@RolesAllowed("ROLE_PATIENT_ACCESS")
public class PatientRestController {

    private static final Logger logger = LoggerFactory.getLogger(PatientRestController.class);
    private static final Logger patientUsageTrans = LoggerFactory.getLogger("PATIENT_USAGE_TRANS");

    private PatientService patientService;

    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/create")
    public HttpEntity<ApiResponse> createPatient(PatientDto patientDto) {
        ResponseEntity<ApiResponse> patient = patientService.createPatient(patientDto);
        return patient;
    }
    /*new */
    @PostMapping("/search/{type}/{value}")
    public HttpEntity<ApiResponse> search(@PathVariable("type") String type,
                                          @PathVariable("value") String value) {
        ResponseEntity<ApiResponse> patient = patientService.search(type, value);
        return patient;
    }

    @PostMapping("/validate/{idNumber}")
    public HttpEntity<ApiResponse> validate(@PathVariable("idNumber") String idNumber) {

        ResponseEntity<ApiResponse> validate = patientService.validate(idNumber);
        return validate;
    }

    @PostMapping("/like-search/{value}")
    public HttpEntity<ApiResponse> likeSearch(@PathVariable("value") String value) {

        ResponseEntity<ApiResponse> patient = patientService.likeSearch(value);
        return patient;
    }

    @PostMapping("/update/{id}")
//    @RolesAllowed("ROLE_PATIENT_REGISTRATION")
    public HttpEntity<ApiResponse> update(@PathVariable("id") String id,
                                          @RequestBody PatientDto patientDto) throws RestValidationException {
        ResponseEntity<ApiResponse> patient = patientService.update(id, patientDto);
        return patient;
    }

    @PostMapping("/register")
//    @RolesAllowed("ROLE_PATIENT_REGISTRATION")
    public HttpEntity<ApiResponse> register(@RequestBody PatientDto patientDto) throws RestValidationException {
        ResponseEntity<ApiResponse> patient = patientService.register(patientDto);
        return patient;
    }
}
