package com.hospital.management.Hospital.management.service;

import com.hospital.management.Hospital.management.entity.Patient;
import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.exception.BadRequestException;
import com.hospital.management.Hospital.management.forms.LoginForm;
import com.hospital.management.Hospital.management.forms.UserForm;
import com.hospital.management.Hospital.management.view.LoginView;
import com.hospital.management.Hospital.management.view.UserView;
import org.springframework.validation.Errors;

import java.util.Collection;


public interface UserService {

  UserView addUser(UserForm userform);

  void delete(Integer userId);

  Collection<User> list();

  LoginView login(LoginForm form, Errors errors);
  LoginView refresh(String refreshToken) throws BadRequestException;

  UserView edit(UserForm userForm, Integer userId);

  Collection<Patient> patientList(Integer id, String localDate);

    Collection<User> doctorDetails(String name);
}
