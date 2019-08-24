package lk.microservices.microservice.one.bussiness.service;

import lk.microservices.microservice.one.bussiness.repository.DefaultPatientDatabaseService;
import lk.microservices.microservice.one.bussiness.util.exception.PatientException;
import lk.microservices.microservice.one.bussiness.model.mapper.PatientMapper;
import lk.microservices.microservice.one.bussiness.model.entity.PatientEntity;
import lk.microservices.microservice.one.bussiness.model.dto.PatientDto;
import lk.microservices.microservice.one.technical.exception.RestValidationException;
import lk.microservices.microservice.one.technical.model.ApiResponse;
import lk.microservices.microservice.one.technical.model.HttpApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static lk.microservices.microservice.one.technical.util.CommonWebUtil.httpApiResponse;

@Service
public class DefaultPatientService implements PatientService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultPatientService.class);
    private DefaultPatientDatabaseService defaultPatientService;

    public DefaultPatientService(DefaultPatientDatabaseService defaultPatientService) {
        this.defaultPatientService = defaultPatientService;
    }

    public ResponseEntity<ApiResponse> createPatient(PatientDto patientDto) {
        PatientEntity patientEntity = defaultPatientService.save(PatientMapper.mapToDto(patientDto));
        return httpApiResponse(new HttpApiResponse(PatientMapper.mapToDto(patientEntity)));
    }


    public ResponseEntity<ApiResponse> search(String type,
                                              String value) {

        PatientEntity patientEntity = null;
        try {
            patientEntity = defaultPatientService.findPatient(type, value);
            return httpApiResponse(new HttpApiResponse(PatientMapper.mapToDto(patientEntity)));
        } catch (PatientException e) {
            logger.error(e.getCode() + ":"+e.getMessage());
            return httpApiResponse(new HttpApiResponse(e.getCode(), e.getMessage()));
        }
    }


    public ResponseEntity<ApiResponse> validate(String idNumber) {
        //HttpApiResponse httpResponse = null;
        Boolean vaildate = null;
        try {
            vaildate = defaultPatientService.validateIdNumberUse(idNumber);
            return httpApiResponse(new HttpApiResponse(vaildate));
        } catch (PatientException e) {
            logger.error(e.getCode() + ":"+e.getMessage());
            return httpApiResponse(new HttpApiResponse(e.getCode(), e.getMessage()));
        }
    }

    public ResponseEntity<ApiResponse> likeSearch(String value) {
        List<PatientEntity> patientEntityList = defaultPatientService.likeSearchPatient(value);
        List<PatientDto> patientDtoList = new ArrayList<PatientDto>();
        for (PatientEntity p : patientEntityList) {
            patientDtoList.add(PatientMapper.mapToDto(p));
        }
        return httpApiResponse(new HttpApiResponse(patientDtoList));
    }

    public ResponseEntity<ApiResponse> update(String id, PatientDto patientDto) throws RestValidationException {
        PatientEntity patientEntity = null;
        try {
            patientEntity = defaultPatientService.updatePatient(id, PatientMapper.mapToDto(patientDto));
            return httpApiResponse(new HttpApiResponse(PatientMapper.mapToDto(patientEntity)));
        } catch (PatientException e) {
            logger.error(e.getCode() + ":"+e.getMessage());
            return httpApiResponse(new HttpApiResponse(e.getCode(), e.getMessage()));
        }
    }

    public ResponseEntity<ApiResponse> register(PatientDto patientDto) throws RestValidationException {
        PatientEntity patientEntity = null;
        try {
            patientEntity = defaultPatientService.patientRegistration(PatientMapper.mapToDto(patientDto));
            return httpApiResponse(new HttpApiResponse(PatientMapper.mapToDto(patientEntity)));
        } catch (PatientException e) {
            logger.error(e.getCode() + ":"+e.getMessage());
            return httpApiResponse(new HttpApiResponse(e.getCode(), e.getMessage()));
        }
    }
}
