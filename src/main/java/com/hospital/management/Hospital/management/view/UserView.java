package com.hospital.management.Hospital.management.view;

import com.hospital.management.Hospital.management.entity.User;


public class UserView {
  private Integer id;
  private  String name;
  private String email;
  private String phoneNumber;
  private String password;
  private  String type;
  private  String speciality;
  private  String education;



  public UserView(User user) {
    this.id= user.getUserId();
    this.name=user.getName();
    this.email=user.getEmail();
    this.phoneNumber=user.getPhoneNumber();
    this.type=user.getType();
    this.speciality=user.getSpeciality();
    this.education=user.getEducation();
  }

  public Integer getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getPassword() {return password;}

  public String getType() {
    return type;
  }

  public String getSpeciality() {
    return speciality;
  }

  public String getEducation() {
    return education;
  }
}
