package lk.microservices.microservice.one.bussiness.repository;

import lk.microservices.microservice.one.bussiness.util.builder.PatientBuilder;
import lk.microservices.microservice.one.bussiness.util.exception.PatientException;
import lk.microservices.microservice.one.bussiness.model.entity.PatientEntity;
import lk.microservices.microservice.one.bussiness.model.entity.UserIdEntity;
import lk.microservices.microservice.one.bussiness.util.RunningNumberService;
import lk.microservices.microservice.one.technical.enums.StatusCode;
import lk.microservices.microservice.one.technical.exception.RestValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class MongoPatientDatabaseService implements PatientDatabaseService {

    private static final Logger logger = LoggerFactory.getLogger(MongoPatientDatabaseService.class);
    private PatientRepository patientRepository;

    private PatientBuilder patientBuilder;
    private RunningNumberService runningNumberService;
    private MongoTemplate mongoTemplate;

    public MongoPatientDatabaseService(PatientRepository patientRepository,
                                       RunningNumberService runningNumberService,
                                       MongoTemplate mongoTemplate,
                                       PatientBuilder builder) {
        this.patientRepository = patientRepository;
        this.runningNumberService = runningNumberService;
        this.mongoTemplate = mongoTemplate;
        this.patientBuilder = builder;
    }

    //@Override
    @Deprecated
    public PatientEntity findOne(String patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    @Override
    public PatientEntity save(PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }

    @Override
    public boolean exists(String patientId) {
        return patientRepository.existsById(patientId);
    }

    @Override
    public PatientEntity patientRegistration(PatientEntity patientEntity) throws RestValidationException, PatientException {

        boolean userIdAvailable = isUserIdAvailable(patientEntity.getUserId());

        if (!userIdAvailable) {
            throw new PatientException(StatusCode.E1000);
        }
        patientEntity.resetNewPatientRegistrationDetails();
        patientEntity.setRegistrationDate(LocalDate.now());
        patientEntity.setLastVisitedDate(LocalDate.now());
        patientEntity.setPatientNumber(runningNumberService.generatePatientNumber());

        if (!patientEntity.parameterValuesValid()) {
            throw new RestValidationException("Patient information is not valid");
        }

        PatientEntity persistedPatientEntity = patientRepository.save(patientEntity);

        return persistedPatientEntity;
    }

    @Override
    public Optional<PatientEntity> findPatientById(String patientId) {
        return patientRepository.findById(patientId);
    }

    @Override
    public PatientEntity findPatient(String type, String searchId) throws PatientException {

        Optional<PatientEntity> patientOpt;
        switch (type) {
            case "userid": {
                if (!searchId.contains(":"))
                    throw new PatientException(StatusCode.E1002, "Search type format invalid");
                String[] split = searchId.split(":");
                String idType = split[0];
                String idNumber = split[1];
                patientOpt = patientRepository.findByUserId(new UserIdEntity(UserIdEntity.IdType.valueOf(idType), idNumber));
                break;
            }
            case "name": {
                List<PatientEntity> patientEntities = patientRepository.findAllByNameLike(searchId);
                patientOpt = patientEntities.stream().findFirst();
                break;
            }
            case "systemuserid": {
                patientOpt = patientRepository.findById(searchId);
                break;
            }
            default: {
                throw new PatientException(StatusCode.E1001);
            }
        }
        if (!patientOpt.isPresent()) {
            throw new PatientException(StatusCode.E2000);
        }
        PatientEntity patientEntity = patientBuilder.build(patientOpt.get());//patientBuilder.buildCoverages(patientOpt.get());
        return patientEntity;
    }

    @Override
    public boolean validateIdNumberUse(String searchId) throws PatientException {
        //PatientRepository patientRepository = this.patientRepository.getPatientRepository();
        if (!searchId.contains(":"))
            throw new PatientException(StatusCode.E1002, "Search type format invalid");
        String[] split = searchId.split(":");
        String idType = split[0];
        String idNumber = split[1];
        boolean exists = patientRepository.existsByUserId(new UserIdEntity(UserIdEntity.IdType.valueOf(idType), idNumber));
        //return new HttpApiResponse(exists);
        return exists;
    }

    @Override
    public PatientEntity updatePatient(String patientId, PatientEntity patientEntityUpdate) throws PatientException {
        Optional<PatientEntity> foundPatient = patientRepository.findById(patientId);
        //Patient patient = patientRepository.getPatientRepository().findOne(patientId);
        if (!foundPatient.isPresent()) {
            throw new PatientException(StatusCode.E2000);
        } else {
            PatientEntity patientEntity = foundPatient.get();
            updatePatientInformation(patientEntity, patientEntityUpdate);
            PatientEntity newPatientEntity = patientRepository.save(patientEntity);
            //return new HttpApiResponse(newPatient);
            return newPatientEntity;
        }
    }

//    @Override
//    public Patient removeCoveragePlan(String systemUserId, PatientCoverage coverage) throws PatientException {
//        Optional<Patient> foundPatient = patientRepository.findById(systemUserId);
//        if (!foundPatient.isPresent()) {
//            throw new PatientException(StatusCode.E2000);
//        } else {
//            Patient patient = foundPatient.get();
//            patient.removeCoverage(coverage);
//            Patient newPatient = patientRepository.save(patient);
//            //return new HttpApiResponse(newPatient);
//            return newPatient;
//        }
//    }

    @Override
    public List<PatientEntity> likeSearchPatient(String value) {
        logger.info("limiting search for 15 list size");
        List<PatientEntity> patientEntities = patientRepository
                .patientLikeSearch(value, new PageRequest(0, 15)); //todo take it to a config
        //return new HttpApiResponse(patients);
        return patientEntities;
    }

    @Override
    public List<PatientEntity> findAll(List<String> patientIds){
        return patientRepository.findByIdIn(patientIds);
    }

    @Override
    public PatientEntity likeSearchPatient(String patientId, String nameOrNirc) {
        Criteria criteria = new Criteria();
        if (nameOrNirc != null) {
            criteria.orOperator(
                    Criteria.where("name").regex(".*" + nameOrNirc.trim() + ".*", "i"),
                    Criteria.where("userId.number").regex(".*" + nameOrNirc.trim() + ".*", "i")
            );
        }

        Query query = new Query(criteria);
        query.addCriteria(Criteria.where("id").is(patientId));
        return mongoTemplate.findOne(query, PatientEntity.class);
    }

    @Override
    public boolean isUserIdAvailable(UserIdEntity userIdEntity) {
        return !mongoTemplate.exists(Query.query(
                Criteria.where("userId.idType").is(userIdEntity.getIdType())
                        .and("userId.number").is(userIdEntity.getNumber())), PatientEntity.class);
    }


    /**
     * Updates the patient information which are allowed part of the update call
     * <p>
     * Does not update the allergies, patientVaccinations, coverages and patientId
     *
     * @param currentPatientEntity
     * @param newPatientEntityInfo
     * @return Patient
     */
    private PatientEntity updatePatientInformation(PatientEntity currentPatientEntity, PatientEntity newPatientEntityInfo) {
        currentPatientEntity.setName(newPatientEntityInfo.getName());
        currentPatientEntity.setDob(newPatientEntityInfo.getDob());
        currentPatientEntity.setUserId(newPatientEntityInfo.getUserId());
        currentPatientEntity.setGender(newPatientEntityInfo.getGender());
        currentPatientEntity.setContactNumber(newPatientEntityInfo.getContactNumber());
        currentPatientEntity.setAddress(newPatientEntityInfo.getAddress());
        currentPatientEntity.setEmailAddress(newPatientEntityInfo.getEmailAddress());
//        currentPatient.setEmergencyContactNumber(newPatientInfo.getEmergencyContactNumber());
//        currentPatient.setCompany(newPatientInfo.getCompany());
        currentPatientEntity.setNationality(newPatientEntityInfo.getNationality());
        currentPatientEntity.setCompanyId(newPatientEntityInfo.getCompanyId());
        currentPatientEntity.setMaritalStatus(newPatientEntityInfo.getMaritalStatus());
        currentPatientEntity.setRemarks(newPatientEntityInfo.getRemarks());
//        currentPatient.setAllergies(newPatientInfo.getAllergies());
        currentPatientEntity.setTitle(newPatientEntityInfo.getTitle());
        currentPatientEntity.setPreferredMethodOfCommunication(newPatientEntityInfo.getPreferredMethodOfCommunication());
        currentPatientEntity.setConsentGiven(newPatientEntityInfo.isConsentGiven());
        currentPatientEntity.setRace(newPatientEntityInfo.getRace());
        currentPatientEntity.setPreferredLanguage(newPatientEntityInfo.getPreferredLanguage());
//        currentPatient.setPrimaryCareNetwork(newPatientInfo.getPrimaryCareNetwork());
//        currentPatient.setOnGoingMedications(newPatientInfo.getOnGoingMedications());

        return currentPatientEntity;

    }


}
