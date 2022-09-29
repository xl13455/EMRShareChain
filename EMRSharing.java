package org.example;

public class EMRSharing {
    private String id;
    private String patientId;
    private String doctorId;
    private long timestamp;

    public EMRSharing() {
    }

    public EMRSharing(String id, String patientId, String doctorId, long timestamp) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "DataSharingEntity{" +
                "id='" + id + '\'' +
                ", patientId='" + patientId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
