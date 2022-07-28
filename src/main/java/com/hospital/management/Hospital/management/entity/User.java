package com.hospital.management.Hospital.management.entity;

import com.hospital.management.Hospital.management.forms.UserForm;
import com.hospital.management.Hospital.management.forms.validation.Password;

import javax.persistence.*;

@Entity
@Table(name="user")
public class User {
  @Id
  @Column(name ="id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name = "email")
  private String email;
  @Column(name = "phone_number")
  private String phoneNumber;
  @Password
  @Column(name = "password")
  private String password;
  @Column(name = "type")
  private String type;
  @Column(name = "department")
  private String speciality;
  @Column(name = "education")
  private String education;


  public User() {
  }

  public User( String name, String email, String phoneNumber, String password, String type, String speciality, String education) {
    this.name=name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.password = password;
    this.type = type;
    this.speciality = speciality;
    this.education = education;
  }
  public  User(UserForm userForm)
  {
    this.name= userForm.getName();
    this.email = userForm.getEmail();
    this.phoneNumber = userForm.getPhoneNumber();
    this.password = userForm.getPassword();
    this.type = userForm.getType();
    this.speciality = userForm.getSpeciality();
    this.education = userForm.getEducation();

  }

  public User(Integer userId) {
    this.id =userId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

