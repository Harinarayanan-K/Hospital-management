package com.hospital.management.Hospital.management.view;

import com.hospital.management.Hospital.management.entity.Patient;
import com.hospital.management.Hospital.management.entity.User;

public class PatientView {

    private Integer patientId;
    private String patientName;
    private String gender;
    private String email;
    private String phoneNumber;
    private String appointmentDate;
    private String time;
    private String doctor;
    private String department;
    private Integer doctorId;
   User user;
    public PatientView(Patient patient) {
        this.patientId = patient.getPatientId();
        this.patientName = patient.getPatientName();
        this.gender = patient.getGender();
        this.email = patient.getEmail();
        this.phoneNumber = patient.getPhoneNumber();
        this.department = patient.getDepartment();
        this.doctorId = patient.getDoctorId();
        this.appointmentDate = patient.getAppointmentDate();
        this.time = patient.getTime();

        try {
            this.doctor = patient.getUser().getName();
        } catch (NullPointerException e) {
            this.doctor = "";
        }


    }

//    public PatientView(Patient patient) {
//        this.patientId = patient.getPatientId();
//        this.patientName = patient.getPatientName();
//        this.gender = patient.getGender();
//        this.email = patient.getEmail();
//        this.phoneNumber = patient.getPhoneNumber();
//        this.department = patient.getDepartment();
//        this.doctorId = patient.getDoctorId();
//        this.appointmentDate = patient.getAppointmentDate();
//        this.time = patient.getTime();
//    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getTime() {
        return time;
    }

    public String getDepartment() {
        return department;
    }



}
