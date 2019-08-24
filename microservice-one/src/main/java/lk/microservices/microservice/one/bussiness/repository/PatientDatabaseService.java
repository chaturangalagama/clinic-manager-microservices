package lk.microservices.microservice.one.bussiness.repository;

import lk.microservices.microservice.one.bussiness.util.exception.PatientException;
import lk.microservices.microservice.one.bussiness.model.entity.PatientEntity;
import lk.microservices.microservice.one.bussiness.model.entity.UserIdEntity;
import lk.microservices.microservice.one.technical.exception.RestValidationException;

import java.util.List;
import java.util.Optional;

public interface PatientDatabaseService {

    PatientEntity save(PatientEntity patientEntity);

    boolean exists(String patientId);

    PatientEntity patientRegistration(PatientEntity patientEntity) throws RestValidationException, PatientException;

    Optional<PatientEntity> findPatientById(String patientId);

    PatientEntity findPatient(String type, String searchId) throws PatientException ;

    boolean validateIdNumberUse(String searchId) throws PatientException ;

    PatientEntity updatePatient(String patientId, PatientEntity patientEntityUpdate) throws PatientException ;

//    Patient removeCoveragePlan(String systemUserId, PatientCoverage coverage) throws PatientException;

    List<PatientEntity> likeSearchPatient(String value);

    PatientEntity likeSearchPatient(String patientId, String nameOrNirc);

    boolean isUserIdAvailable(UserIdEntity userIdEntity);

    List<PatientEntity> findAll(List<String> patientIds);
}
