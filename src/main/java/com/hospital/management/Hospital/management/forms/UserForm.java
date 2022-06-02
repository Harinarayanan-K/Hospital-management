package com.hospital.management.Hospital.management.forms;


import javax.validation.constraints.NotBlank;

public class UserForm {

  @NotBlank
  private String name;
  @NotBlank
  private  String email;
  private String  phoneNumber;
  @NotBlank
  private String password;

  @NotBlank
  private String type;

  @NotBlank
  private String speciality;

  @NotBlank
  private String education;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String speciality) {
    this.speciality = speciality;
  }

  public String getEducation() {
    return education;
  }

  public void setEducation(String education) {
    this.education = education;
  }
}
