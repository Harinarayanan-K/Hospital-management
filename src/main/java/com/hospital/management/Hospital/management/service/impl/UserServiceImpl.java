package com.hospital.management.Hospital.management.service.impl;

import com.hospital.management.Hospital.management.entity.Attendence;
import com.hospital.management.Hospital.management.entity.Patient;
import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.exception.BadRequestException;
import com.hospital.management.Hospital.management.forms.LeaveForm;
import com.hospital.management.Hospital.management.forms.LoginForm;
import com.hospital.management.Hospital.management.forms.UserForm;
import com.hospital.management.Hospital.management.repository.AttendenceRepository;
import com.hospital.management.Hospital.management.repository.PatientRepository;
import com.hospital.management.Hospital.management.repository.UserRepository;
import com.hospital.management.Hospital.management.security.config.SecurityConfig;
import com.hospital.management.Hospital.management.security.util.InvalidTokenException;
import com.hospital.management.Hospital.management.security.util.TokenExpiredException;
import com.hospital.management.Hospital.management.security.util.TokenGenerator;
import com.hospital.management.Hospital.management.service.UserService;
import com.hospital.management.Hospital.management.view.LeaveView;
import com.hospital.management.Hospital.management.view.LoginView;
import com.hospital.management.Hospital.management.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.Collection;

import static com.hospital.management.Hospital.management.security.AccessTokenUserDetailsService.PURPOSE_ACCESS_TOKEN;

@Service
public class UserServiceImpl implements UserService {


  private static final String PURPOSE_REFRESH_TOKEN = "REFRESH_TOKEN";

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private AttendenceRepository attendenceRepository;

  @Autowired
  private TokenGenerator tokenGenerator;

  @Autowired
  private SecurityConfig securityConfig;



  public UserView addUser(UserForm userform) {


      return new UserView(userRepository.save(new User(userform.getName(), userform.getEmail(),userform.getPhoneNumber(), passwordEncoder.encode(userform.getPassword()), userform.getType(), userform.getSpeciality(), userform.getEducation())));
    }


  @Override
  public void delete(Integer userId) {
    userRepository.delete( userRepository.findById( userId ) );
  }

  @Override
  public Collection<User> list() {
    return userRepository.findAll();

  }

  @Override
  public LoginView login(LoginForm form, Errors errors) throws BadRequestException {
    if (errors.hasErrors()) {
      throw badRequestException();
    }
    User user = userRepository.findByEmail( form.getEmail() ).orElseThrow( UserServiceImpl::badRequestException );
    if (!passwordEncoder.matches( form.getPassword(), user.getPassword() )) {
      throw badRequestException();
    }

    String id = String.format( "%010d", user.getId() );
    TokenGenerator.Token accessToken = tokenGenerator.create( PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry() );
    TokenGenerator.Token refreshToken = tokenGenerator.create( PURPOSE_REFRESH_TOKEN, id + user.getPassword(), securityConfig.getRefreshTokenExpiry() );
    return new LoginView( user, accessToken, refreshToken );
  }

  @Override
  public LoginView refresh(String refreshToken) throws BadRequestException {
    TokenGenerator.Status status;
    try {
      status = tokenGenerator.verify( PURPOSE_REFRESH_TOKEN, refreshToken );
    } catch (InvalidTokenException e) {
      throw new BadRequestException( "Invalid token", e );
    } catch (TokenExpiredException e) {
      throw new BadRequestException( "Token expired", e );
    }

    int userId;
    try {
      userId = Integer.parseInt( status.data.substring( 0, 10 ) );
    } catch (NumberFormatException e) {
      throw new BadRequestException( "Invalid token", e );
    }

    String password = status.data.substring( 10 );

    User user = userRepository.findByIdAndPassword( userId, password ).orElseThrow( UserServiceImpl::badRequestException );

    String id = String.format( "%010d", user.getId() );
    TokenGenerator.Token accessToken = tokenGenerator.create( PURPOSE_ACCESS_TOKEN, id, securityConfig.getAccessTokenExpiry() );
    return new LoginView(
      user,
      new LoginView.TokenView( ((TokenGenerator.Token) accessToken).value, accessToken.expiry ),
      new LoginView.TokenView( refreshToken, status.expiry )
    );
  }

  @Override
  public UserView edit(UserForm userForm, Integer userId) {
    User user=userRepository.findById(userId);
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(passwordEncoder.encode((userForm.getPassword())));
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setSpeciality(userForm.getSpeciality());
    user.setEducation(userForm.getEducation());
    userRepository.save(user);
    return new UserView(user);
  }

  @Override
  public Collection<Patient> patientList(Integer id,String localDate) {
    System.out.println("date"+localDate);

    System.out.println("serviceimp"+id);
    return patientRepository.findByDoctorIdByAppointmentDate(id,localDate);
  }
  @Override
  public Collection<User> doctorDetails(String name) {
    System.out.println(name);
    return userRepository.findByName(name);
  }
  @Override
  public LeaveView leaveReq(LeaveForm leaveForm){
  return new LeaveView(attendenceRepository.save(new Attendence(leaveForm)));
  }

  @Override
  public Collection<Attendence> leaveList() {
    return attendenceRepository.findByStatus();
  }
  @Override
  public LeaveView updateStatus(Integer attendenceId) {
    Attendence attendence=attendenceRepository.findByAttendenceIdAndStatus(attendenceId, Attendence.Status.PENDING.value);
    attendence.setStatus(Attendence.Status.ABSENT.value);
    return new LeaveView(attendenceRepository.save(attendence));
  }
  @Override
  public Collection<Attendence> approvedLeaves() {
    return attendenceRepository.findByStatus(Attendence.Status.ABSENT.value);
  }
  @Override
  public Collection<Attendence> leaveStatus(Integer doctorId) {
    return attendenceRepository.findByStatusAndDoctorId(doctorId);
  }
  @Override
  public LeaveView updateStatusToReject(Integer attendenceId) {
    Attendence attendence=attendenceRepository.findByAttendenceIdAndStatus(attendenceId, Attendence.Status.PENDING.value);
    attendence.setStatus(Attendence.Status.REJECT.value);
    return new LeaveView(attendenceRepository.save(attendence));
  }

  @Override
  public User findEmail(String emailData) {
    return userRepository.findEmailByEmail(emailData);
  }

  private static BadRequestException badRequestException() {
    return new BadRequestException( "Invalid credentials" );
  }
}



