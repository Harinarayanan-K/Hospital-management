package com.hospital.management.Hospital.management.service;

import com.hospital.management.Hospital.management.entity.Attendence;
import com.hospital.management.Hospital.management.entity.Patient;
import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.exception.BadRequestException;
import com.hospital.management.Hospital.management.forms.LeaveForm;
import com.hospital.management.Hospital.management.forms.LoginForm;
import com.hospital.management.Hospital.management.forms.UserForm;
import com.hospital.management.Hospital.management.view.LeaveView;
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

    LeaveView leaveReq(LeaveForm leaveForm);

  Collection<Attendence> leaveList();

  LeaveView updateStatus(Integer attendenceId);

  Collection<Attendence> approvedLeaves();

  Collection<Attendence> leaveStatus(Integer doctorId);

  LeaveView updateStatusToReject(Integer attendenceId );

  User findEmail(String emailData);
}
