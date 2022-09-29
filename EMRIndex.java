package org.example;

import java.util.Arrays;

public class EMRIndex {

    private String emrId;

    private String emrEncHash;

    private String keyEncHash;

    private byte[] keywordEnc_I0;

    private byte[] keywordEnc_I1;

    private String cloudSaveUrl;

    private String patientId;

    private String doctorId;

    private String hcspId;

    private String emrEncDsByPatient;

    private String emrEncDsByDoctor;

    private String cloudSaveUrlDsByPatient;

    private String cloudSaveUrlDsByHcsp;

    private long timeStamp;

    public EMRIndex() {
    }

    public EMRIndex(String emrId, String emrEncHash, String keyEncHash, byte[] keywordEnc_I0, byte[] keywordEnc_I1, String cloudSaveUrl, String patientId, String doctorId, String hcspId, String emrEncDsByPatient, String emrEncDsByDoctor, String cloudSaveUrlDsByPatient, String cloudSaveUrlDsByHcsp, long timeStamp) {
        this.emrId = emrId;
        this.emrEncHash = emrEncHash;
        this.keyEncHash = keyEncHash;
        this.keywordEnc_I0 = keywordEnc_I0;
        this.keywordEnc_I1 = keywordEnc_I1;
        this.cloudSaveUrl = cloudSaveUrl;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.hcspId = hcspId;
        this.emrEncDsByPatient = emrEncDsByPatient;
        this.emrEncDsByDoctor = emrEncDsByDoctor;
        this.cloudSaveUrlDsByPatient = cloudSaveUrlDsByPatient;
        this.cloudSaveUrlDsByHcsp = cloudSaveUrlDsByHcsp;
        this.timeStamp = timeStamp;
    }

    public String getEmrId() {
        return emrId;
    }

    public void setEmrId(String emrId) {
        this.emrId = emrId;
    }

    public String getEmrEncHash() {
        return emrEncHash;
    }

    public void setEmrEncHash(String emrEncHash) {
        this.emrEncHash = emrEncHash;
    }

    public String getKeyEncHash() {
        return keyEncHash;
    }

    public void setKeyEncHash(String keyEncHash) {
        this.keyEncHash = keyEncHash;
    }

    public byte[] getKeywordEnc_I0() {
        return keywordEnc_I0;
    }

    public void setKeywordEnc_I0(byte[] keywordEnc_I0) {
        this.keywordEnc_I0 = keywordEnc_I0;
    }

    public byte[] getKeywordEnc_I1() {
        return keywordEnc_I1;
    }

    public void setKeywordEnc_I1(byte[] keywordEnc_I1) {
        this.keywordEnc_I1 = keywordEnc_I1;
    }

    public String getCloudSaveUrl() {
        return cloudSaveUrl;
    }

    public void setCloudSaveUrl(String cloudSaveUrl) {
        this.cloudSaveUrl = cloudSaveUrl;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getHcspId() {
        return hcspId;
    }

    public void setHcspId(String hcspId) {
        this.hcspId = hcspId;
    }

    public String getEmrEncDsByPatient() {
        return emrEncDsByPatient;
    }

    public void setEmrEncDsByPatient(String emrEncDsByPatient) {
        this.emrEncDsByPatient = emrEncDsByPatient;
    }

    public String getEmrEncDsByDoctor() {
        return emrEncDsByDoctor;
    }

    public void setEmrEncDsByDoctor(String emrEncDsByDoctor) {
        this.emrEncDsByDoctor = emrEncDsByDoctor;
    }

    public String getCloudSaveUrlDsByPatient() {
        return cloudSaveUrlDsByPatient;
    }

    public void setCloudSaveUrlDsByPatient(String cloudSaveUrlDsByPatient) {
        this.cloudSaveUrlDsByPatient = cloudSaveUrlDsByPatient;
    }

    public String getCloudSaveUrlDsByHcsp() {
        return cloudSaveUrlDsByHcsp;
    }

    public void setCloudSaveUrlDsByHcsp(String cloudSaveUrlDsByHcsp) {
        this.cloudSaveUrlDsByHcsp = cloudSaveUrlDsByHcsp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "EMRIndex{" +
                "emrId='" + emrId + '\'' +
                ", emrEncHash='" + emrEncHash + '\'' +
                ", keyEncHash='" + keyEncHash + '\'' +
                ", keywordEnc_I0=" + Arrays.toString(keywordEnc_I0) +
                ", keywordEnc_I1=" + Arrays.toString(keywordEnc_I1) +
                ", cloudSaveUrl='" + cloudSaveUrl + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", hcspId='" + hcspId + '\'' +
                ", emrEncDsByPatient='" + emrEncDsByPatient + '\'' +
                ", emrEncDsByDoctor='" + emrEncDsByDoctor + '\'' +
                ", cloudSaveUrlDsByPatient='" + cloudSaveUrlDsByPatient + '\'' +
                ", cloudSaveUrlDsByHcsp='" + cloudSaveUrlDsByHcsp + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
