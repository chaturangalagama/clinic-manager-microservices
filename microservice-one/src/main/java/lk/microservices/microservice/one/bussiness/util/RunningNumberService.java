package lk.microservices.microservice.one.bussiness.util;

public interface RunningNumberService {

    String generateCaseNumber();

    String generateSalesOrderNumber();

    String generateInvoiceNumber();

    String generatePatientNumber();

    String generateBillNumber();

    String generateVisitNumber();

    String generateAdjustmentNumber();

    String generateMedicalCertificateNumber();

    String generateRequestNumber();

    String generateOrderNumber();

    String generateGRNNumber();

    String generateGRVNNumber();

    String generateDeliveryNote();

    String generateDeliveryVoidNote();

    long queueNextNumber(String clinicCode, byte prefix);

    String generateClaimRefNumber();
}
