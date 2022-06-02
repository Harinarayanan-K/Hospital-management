package com.hospital.management.Hospital.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hospital.management.Hospital.management.forms.PatientForm;

import javax.persistence.*;


@Table(name ="Patient")
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Integer patientId;

    @Column(name = "patient_name")
    private String patientName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_no")
    private String phoneNumber;

    @Column(name = "department")
    private String department;

    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "appointment_date")

    private String appointmentDate;

    @Column(name = "time")
    private String time;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "doctor_id", nullable = true, insertable = false, updatable = false)
    private User user;


    public Patient() {
    }

    public Patient(String patientName, String gender, String email, String phoneNumber, String department, Integer doctorId, String appointmentDate, String time) {


        this.patientName = patientName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.department = department;
        this.doctorId = doctorId;
        this.appointmentDate = appointmentDate;
        this.time = time;

    }

    public Patient(PatientForm form) {
        this.patientName = form.getPatientName();
        this.gender = form.getGender();
        this.email = form.getEmail();
        this.phoneNumber = form.getPhoneNumber();
        this.department = form.getDepartment();
        this.doctorId = form.getDoctorId();
        this.appointmentDate = form.getAppointmentDate();
        this.time = form.getTime();

    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
