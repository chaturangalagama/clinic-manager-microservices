package lk.microservices.microservice.one.bussiness.model.mapper;

import lk.microservices.microservice.one.bussiness.model.dto.AddressDto;
import lk.microservices.microservice.one.bussiness.model.dto.ContactNumberDto;
import lk.microservices.microservice.one.bussiness.model.dto.PatientDto;
import lk.microservices.microservice.one.bussiness.model.dto.Status;
import lk.microservices.microservice.one.bussiness.model.entity.AddressEntity;
import lk.microservices.microservice.one.bussiness.model.entity.ContactNumberEntity;
import lk.microservices.microservice.one.bussiness.model.entity.PatientEntity;

import java.time.LocalDate;

public class PatientMapper extends Mapper {

    /**
     * Takes todays date as registration date
     * Does not copy over FileMetaDateEntity object
     *
     * @param patientDto
     * @return
     */
    public static PatientEntity mapToDto(PatientDto patientDto) {
        if(patientDto == null){
            return null;
        }
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientDto.getId());
        patientEntity.setRegistrationDate(LocalDate.now());
        patientEntity.setName(patientDto.getName());
        patientEntity.setDob(patientDto.getDob());
        patientEntity.setEmailAddress(patientDto.getEmailAddress());
        patientEntity.setRemarks(patientDto.getRemarks());
        patientEntity.setLastVisitedDate(LocalDate.now());
        patientEntity.setStatus(Status.ACTIVE);
        patientEntity.setTitle(patientDto.getTitle());
        patientEntity.setPreferredMethodOfCommunication(patientDto.getPreferredMethodOfCommunication());

        if(patientDto.getUserId() != null)
            patientEntity.setUserId(mapToEntityUserId(patientDto.getUserId()));
        if(patientDto.getGender() != null)
            patientEntity.setGender(PatientEntity.Gender.valueOf(patientDto.getGender().name()));
        if(patientDto.getContactNumber() != null)
            patientEntity.setContactNumber(new ContactNumberEntity(patientDto.getContactNumber().getNumber()));
        if(patientDto.getMaritalStatus() != null)
            patientEntity.setMaritalStatus(PatientEntity.MaritalStatus.valueOf(patientDto.getMaritalStatus().name()));

        AddressDto addressDto = patientDto.getAddress();
        if(addressDto != null)
            patientEntity.setAddress(new AddressEntity(addressDto.getAddress(), addressDto.getCountry(), addressDto.getPostalCode()));

        //        com.ilt.cms.api.entity.common.ContactNumber secondaryNumber = patientDto.getSecondaryNumber();
//        if(secondaryNumber != null)
//            patientEntity.setSecondaryNumber(new ContactNumberEntity(secondaryNumber.getNumber()));
//        com.ilt.cms.api.entity.patient.EmergencyContactNumber emergencyContactNumber = patientEntity.getEmergencyContactNumber();
//        if(emergencyContactNumber != null) {
//            patient.setEmergencyContactNumber(new EmergencyContactNumber(emergencyContactNumber.getName(),
//                    Relationship.valueOf(emergencyContactNumber.getRelationship().name()), emergencyContactNumber.getNumber()));
//        }
//        com.ilt.cms.api.entity.patient.PatientCompany company = patientEntity.getCompany();
//        if(company != null) {
//            patient.setCompany(new PatientCompany(company.getName(), company.getAddress(), company.getPostalCode(), company.getOccupation()));
//        }
//        patient.setNationality(patientEntity.getNationality());
//        patient.setAllergies(patientEntity.getAllergies().stream()
//                .map(mapAllergy())
//                .collect(Collectors.toList()));
//        patient.setConsentGiven(patientEntity.isConsentGiven());
//        patient.setRace(patientEntity.getRace());
//        patient.setPreferredLanguage(patientEntity.getPreferredLanguage());
//        if(patientEntity.getPatientVaccinations() != null){
//            patient.setPatientVaccinations(patientEntity.getPatientVaccinations().stream().map(PatientMapper::mapToPatientVaccinationCore).collect(Collectors.toList()));
//        }
//        if(patientEntity.getFileMetaData() != null){
//            patient.setFileMetaData(patientEntity.getFileMetaData().stream().map(PatientMapper::mapToFileMetaDataCore).collect(Collectors.toList()));
//        }
//        if(patientEntity.getPrimaryCareNetwork() != null){
//            patient.setPrimaryCareNetwork(mapToPrimaryCareNetworkCore(patientEntity.getPrimaryCareNetwork()));
//        }
//        if(patientEntity.getOnGoingMedications() != null){
//            patient.setOnGoingMedications(patientEntity.getOnGoingMedications().stream()
//                    .map(onGoingMedicationEntity ->  mapToOnGoingMedicationCore(onGoingMedicationEntity))
//                    .collect(Collectors.toList()));
//        }
        return patientEntity;
    }

    public static PatientDto mapToDto(PatientEntity patientEntity) {
        if(patientEntity == null){
            return null;
        }
        PatientDto patientDto = new PatientDto();
        patientDto.setId(patientEntity.getId());
        patientDto.setRegistrationDate(patientEntity.getRegistrationDate());
        patientDto.setName(patientEntity.getName());
        patientDto.setDob(patientEntity.getDob());
        patientDto.setEmailAddress(patientEntity.getEmailAddress());
        patientDto.setRemarks(patientEntity.getRemarks());
        patientDto.setLastVisitedDate(LocalDate.now());
        patientDto.setStatus(Status.ACTIVE);
        patientDto.setTitle(patientEntity.getTitle());

        if(patientEntity.getUserId() != null)
            patientDto.setUserId(mapToDtoUserId(patientEntity.getUserId()));
        if(patientEntity.getGender() != null)
            patientDto.setGender(PatientDto.Gender.valueOf(patientEntity.getGender().name()));
        if(patientEntity.getContactNumber() != null)
            patientDto.setContactNumber(new ContactNumberDto(patientEntity.getContactNumber().getNumber()));
        if(patientEntity.getMaritalStatus() != null)
            patientDto.setMaritalStatus(PatientDto.MaritalStatus.valueOf(patientEntity.getMaritalStatus().name()));

        AddressEntity address = patientEntity.getAddress();
        if(address != null)
            patientDto.setAddress(new AddressDto(address.getAddress(), address.getCountry(), address.getPostalCode()));

//        patientDto.setPreferredMethodOfCommunication(PatientEntity.getPreferredMethodOfCommunication());
//        EmergencyContactNumber emergencyContactNumber = patient.getEmergencyContactNumber();
//        if(emergencyContactNumber != null) {
//            patientEntity.setEmergencyContactNumber(new com.ilt.cms.api.entity.patient.EmergencyContactNumber(emergencyContactNumber.getName(),
//                    com.ilt.cms.api.entity.common.Relationship.valueOf(String.valueOf(emergencyContactNumber.getRelationship())),
//                            emergencyContactNumber.getNumber()));
//        }
//        PatientCompany company = patient.getCompany();
//        if(company != null) {
//            patientEntity.setCompany(new com.ilt.cms.api.entity.patient.PatientCompany(
//                    company.getName(), company.getAddress(), company.getPostalCode(), company.getOccupation()));
//        }
//        patientEntity.setNationality(patient.getNationality());
//        patientEntity.setAllergies(patient.getAllergies().stream()
//                .map(mapAllergyFromCore())
//                .collect(Collectors.toList()));
//        patientEntity.setConsentGiven(patient.isConsentGiven());
//        patientEntity.setRace(patient.getRace());
//        patientEntity.setPreferredLanguage(patient.getPreferredLanguage());
//        ContactNumber secondaryNumber = patient.getSecondaryNumber();
//        if(secondaryNumber != null) {
//            patientEntity.setSecondaryNumber(new com.ilt.cms.api.entity.common.ContactNumber(secondaryNumber.getNumber()));
//        }
//        if(patient.getPatientVaccinations() != null) {
//            patientEntity.setPatientVaccinations(patient.getPatientVaccinations().stream().map(PatientMapper::mapToPatientVaccinationEntity).collect(Collectors.toList()));
//        }
//        if(patient.getFileMetaData() != null) {
//            patientEntity.setFileMetaData(patient.getFileMetaData().stream().map(PatientMapper::mapToFileMetaDataEntity).collect(Collectors.toList()));
//        }
//        if(patient.getPrimaryCareNetwork() != null){
//            patientEntity.setPrimaryCareNetwork(mapToPrimaryCareNetworkEntity(patient.getPrimaryCareNetwork()));
//        }
//        if(patient.getOnGoingMedications() != null){
//            patientEntity.setOnGoingMedications(patient.getOnGoingMedications().stream()
//                    .map(onGoingMedication ->  mapToOnGoingMedicationEntity(onGoingMedication))
//                    .collect(Collectors.toList()));
//        }
        return patientDto;
    }

//    private static Function<com.ilt.cms.api.entity.patient.PatientAllergy, PatientAllergy> mapAllergy() {
//        return patientAllergy ->
//                new PatientAllergy(PatientAllergy.AllergyType.valueOf(patientAllergy.getAllergyType().name()),
//                        patientAllergy.getName(), patientAllergy.getRemarks(), patientAllergy.getAddedDate());
//    }
//
//    private static Function<PatientAllergy, com.ilt.cms.api.entity.patient.PatientAllergy> mapAllergyFromCore() {
//        return patientAllergy ->
//                new com.ilt.cms.api.entity.patient.PatientAllergy(com.ilt.cms.api.entity.patient.PatientAllergy.AllergyType.valueOf(patientAllergy.getAllergyType().name()),
//                        patientAllergy.getName(), patientAllergy.getRemarks(), patientAllergy.getAddedDate());
//    }
//
//    private static OnGoingMedication mapToOnGoingMedicationCore(com.ilt.cms.api.entity.patient.OnGoingMedication onGoingMedicationEntity){
//        if(onGoingMedicationEntity == null){
//            return null;
//        }
//        OnGoingMedication onGoingMedication = new OnGoingMedication();
//        onGoingMedication.setItemCode(onGoingMedicationEntity.getItemCode());
//        onGoingMedication.setMedicationName(onGoingMedicationEntity.getMedicationName());
//        onGoingMedication.setStartDate(onGoingMedicationEntity.getStartDate());
//        if(onGoingMedicationEntity.getType() != null) {
//            onGoingMedication.setType(OnGoingMedication.OnGoingType.valueOf(onGoingMedicationEntity.getType().name()));
//        }
//        return onGoingMedication;
//    }
//
//    private static com.ilt.cms.api.entity.patient.OnGoingMedication mapToOnGoingMedicationEntity(OnGoingMedication onGoingMedication){
//        if(onGoingMedication == null){
//            return null;
//        }
//        com.ilt.cms.api.entity.patient.OnGoingMedication onGoingMedicationEntity = new com.ilt.cms.api.entity.patient.OnGoingMedication();
//        onGoingMedicationEntity.setItemCode(onGoingMedication.getItemCode());
//        onGoingMedicationEntity.setMedicationName(onGoingMedication.getMedicationName());
//        onGoingMedicationEntity.setStartDate(onGoingMedication.getStartDate());
//        if(onGoingMedication.getType() != null) {
//            onGoingMedicationEntity.setType(com.ilt.cms.api.entity.patient.OnGoingMedication.OnGoingType.valueOf(onGoingMedication.getType().name()));
//        }
//        return onGoingMedicationEntity;
//    }
//
//    public static PatientVaccination mapToPatientVaccinationCore(com.ilt.cms.api.entity.patient.PatientVaccination patientVaccinationEntity){
//        if(patientVaccinationEntity == null){
//            return null;
//        }
//        PatientVaccination patientVaccination = new PatientVaccination();
//        patientVaccination.setId(patientVaccinationEntity.getId());
//        patientVaccination.setDoctorId(patientVaccinationEntity.getDoctorId());
//        patientVaccination.setVaccineId(patientVaccinationEntity.getVaccineId());
//        patientVaccination.setGivenDate(patientVaccinationEntity.getGivenDate());
//        patientVaccination.setPlaceGiven(patientVaccinationEntity.getPlaceGiven());
//        patientVaccination.setVaccinationSchedules(
//                patientVaccinationEntity.getVaccinationSchedules().stream().map(PatientMapper.mapToVaccinationScheduleCore()).collect(Collectors.toList())
//        );
//        return patientVaccination;
//    }
//
//    public static com.ilt.cms.api.entity.patient.PatientVaccination mapToPatientVaccinationEntity(PatientVaccination patientVaccination){
//        if(patientVaccination == null){
//            return null;
//        }
//        com.ilt.cms.api.entity.patient.PatientVaccination patientVaccinationEntity = new com.ilt.cms.api.entity.patient.PatientVaccination();
//        patientVaccinationEntity.setId(patientVaccination.getId());
//        patientVaccinationEntity.setDoctorId(patientVaccination.getDoctorId());
//        patientVaccinationEntity.setVaccineId(patientVaccination.getVaccineId());
//        patientVaccinationEntity.setGivenDate(patientVaccination.getGivenDate());
//        patientVaccinationEntity.setPlaceGiven(patientVaccination.getPlaceGiven());
//        patientVaccinationEntity.setVaccinationSchedules(
//                patientVaccination.getVaccinationSchedules().stream().map(PatientMapper.mapToVaccinationScheduleEntity()).collect(Collectors.toList())
//        );
//        return patientVaccinationEntity;
//    }
//
//    public static FileMetaData mapToFileMetaDataCore(FileMetadataEntity fileMetadataEntity){
//        if(fileMetadataEntity == null){
//            return null;
//        }
//        FileMetaData fileMetaData = new FileMetaData(fileMetadataEntity.decodedFileIdValue(), fileMetadataEntity.getName()
//                , fileMetadataEntity.getFileName(), fileMetadataEntity.getUploader(), fileMetadataEntity.getClinicId(),
//                fileMetadataEntity.getType(), fileMetadataEntity.getSize(), fileMetadataEntity.getDescription());
//        return fileMetaData;
//
//    }
//
//    public static FileMetadataEntity mapToFileMetaDataEntity(FileMetaData fileMetadata){
//        if(fileMetadata == null){
//            return null;
//        }
//        FileMetadataEntity fileMetadataEntity = new FileMetadataEntity(fileMetadata.getFileId(), fileMetadata.getName()
//                , fileMetadata.getFileName(), fileMetadata.getUploader(), fileMetadata.getClinicId(),
//                fileMetadata.getType(), fileMetadata.getSize(), fileMetadata.getDescription());
//        return fileMetadataEntity;
//
//    }
//
//    private static Function<com.ilt.cms.api.entity.patient.VaccinationSchedule, VaccinationSchedule> mapToVaccinationScheduleCore(){
//        return vaccinationScheduleEntity ->
//                new VaccinationSchedule(vaccinationScheduleEntity.getVaccineId(), vaccinationScheduleEntity.getScheduledDate());
//    }
//
//    private static Function<VaccinationSchedule, com.ilt.cms.api.entity.patient.VaccinationSchedule> mapToVaccinationScheduleEntity(){
//        return vaccinationSchedule ->
//                new com.ilt.cms.api.entity.patient.VaccinationSchedule(vaccinationSchedule.getVaccineId(), vaccinationSchedule.getScheduledDate());
//    }
//
//    private static PrimaryCareNetwork mapToPrimaryCareNetworkCore(com.ilt.cms.api.entity.patient.PrimaryCareNetwork primaryCareNetworkEntity){
//        return new PrimaryCareNetwork(primaryCareNetworkEntity.isOptIn(), primaryCareNetworkEntity.isOptOut(), primaryCareNetworkEntity.getOptInDate());
//    }
//
//    private static com.ilt.cms.api.entity.patient.PrimaryCareNetwork mapToPrimaryCareNetworkEntity(PrimaryCareNetwork primaryCareNetwork){
//        return new com.ilt.cms.api.entity.patient.PrimaryCareNetwork(primaryCareNetwork.isOptIn(),
//                primaryCareNetwork.isOptOut(), primaryCareNetwork.getOptInDate());
//    }
}
