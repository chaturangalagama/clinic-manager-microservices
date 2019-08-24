package lk.microservices.microservice.one.bussiness.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lk.microservices.microservice.one.bussiness.util.Constant;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PatientDto {

    public enum Gender {
        MALE, FEMALE, OTHER
    }

    public enum MaritalStatus {
        SINGLE, MARRIED, DIVORCED, WIDOWED, SEPARATED, OTHER
    }
    private String id;
    private String title;
    private String preferredMethodOfCommunication;
    private boolean consentGiven;
    private String race;
    private String preferredLanguage;
    private String patientNumber;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= Constant.JSON_DATE_FORMAT)
    @DateTimeFormat(pattern = Constant.JSON_DATE_FORMAT)
    private LocalDate registrationDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=Constant.JSON_DATE_FORMAT)
    @DateTimeFormat(pattern = Constant.JSON_DATE_FORMAT)
    private LocalDate lastVisitedDate;
    private String name;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=Constant.JSON_DATE_FORMAT)
    @DateTimeFormat(pattern = Constant.JSON_DATE_FORMAT)
    private LocalDate dob;
    private UserIdDto userId;
    private Gender gender;
    private ContactNumberDto contactNumber;
//    private ContactNumberDto secondaryNumber;
    private Status status;
    private AddressDto address;
    private String emailAddress;
//    private EmergencyContactNumber emergencyContactNumber;
//    private PatientCompany company;
    private String nationality;
    private String companyId;
    private MaritalStatus maritalStatus;
    private String remarks;
//    private List<PatientAllergy> allergies = new ArrayList<>();
//    private List<PatientVaccination> patientVaccinations = new ArrayList<>();
//
//    private List<FileMetadataEntity> fileMetaData = new ArrayList<>();
}
