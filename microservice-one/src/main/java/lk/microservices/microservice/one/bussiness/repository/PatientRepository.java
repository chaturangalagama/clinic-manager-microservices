package lk.microservices.microservice.one.bussiness.repository;

import lk.microservices.microservice.one.bussiness.model.entity.PatientEntity;
import lk.microservices.microservice.one.bussiness.model.entity.UserIdEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<PatientEntity, String> {

    Optional<PatientEntity> findByUserId(UserIdEntity userId);

    @Query("{'userId.number' : ?0}")
    PatientEntity findByUserId(String userId);

    boolean existsByUserId(UserIdEntity userId);

    List<PatientEntity> findAllByNameLike(String name);

    @ExistsQuery(value = "{ 'coverages.medicalCoverageId' : ?0 }")
    boolean isMedicalServiceIdUsed(String medicalCoverageId);

    @ExistsQuery(value = "{ 'coverages.medicalCoverageId' : ?0, 'coverages.planId' : ?1}")
    boolean isMedicalPlanIdUsed(String medicalCoverageId, String planId);

    @Query("{ $text: {$search: ?0 } }")
    List<PatientEntity> patientLikeSearch(String value, Pageable pageable);

    List<PatientEntity> findByIdIn(List<String> patientIds);
}