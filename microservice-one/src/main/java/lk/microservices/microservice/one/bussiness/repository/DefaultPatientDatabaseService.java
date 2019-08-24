package lk.microservices.microservice.one.bussiness.repository;

import lk.microservices.microservice.one.bussiness.service.PatientService;
import lk.microservices.microservice.one.bussiness.util.exception.PatientException;
import lk.microservices.microservice.one.bussiness.model.entity.PatientEntity;
import lk.microservices.microservice.one.bussiness.repository.PatientDatabaseService;
import lk.microservices.microservice.one.bussiness.util.RunningNumberService;
import lk.microservices.microservice.one.technical.exception.RestValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPatientDatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);
    private RunningNumberService runningNumberService;
    private PatientDatabaseService databaseService;


    public DefaultPatientDatabaseService(RunningNumberService runningNumberService, PatientDatabaseService databaseService) {
        this.runningNumberService = runningNumberService;
        this.databaseService = databaseService;
    }

    public PatientEntity save(PatientEntity patientEntity) {
        logger.info("Saving new patient [" + patientEntity + "]");
        String patientNumber = runningNumberService.generatePatientNumber();
        logger.debug("generated new number [{}]", patientNumber);
        patientEntity.setPatientNumber(patientNumber);
        return databaseService.save(patientEntity);
    }

    public PatientEntity patientRegistration(PatientEntity patientEntity) throws RestValidationException, PatientException {
        return databaseService.patientRegistration(patientEntity);
    }

    public PatientEntity findPatientById(String patientId) {
        Optional<PatientEntity> patientOpt = databaseService.findPatientById(patientId);
        return patientOpt.orElse(null);
    }

    public PatientEntity findPatient(String type, String searchId) throws PatientException {
        return databaseService.findPatient(type, searchId);
    }

    public boolean validateIdNumberUse(String searchId) throws PatientException {
        return databaseService.validateIdNumberUse(searchId);
    }

    public PatientEntity updatePatient(String patientId, PatientEntity patientEntityUpdate) throws PatientException {
        return databaseService.updatePatient(patientId, patientEntityUpdate);
    }

//    public Patient removeCoveragePlan(String systemUserId, PatientCoverage coverage) throws PatientException {
//        return databaseService.removeCoveragePlan(systemUserId, coverage);
//    }

    public List<PatientEntity> likeSearchPatient(String value) {
        return databaseService.likeSearchPatient(value);
    }

    public List<PatientEntity> findAll(List<String> patientIds) {
        return databaseService.findAll(patientIds);
    }
}
