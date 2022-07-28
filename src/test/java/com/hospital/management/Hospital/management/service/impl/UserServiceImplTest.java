package com.hospital.management.Hospital.management.service.impl;

import com.hospital.management.Hospital.management.entity.Attendence;
import com.hospital.management.Hospital.management.entity.Patient;
import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.forms.LeaveForm;
import com.hospital.management.Hospital.management.forms.PatientForm;
import com.hospital.management.Hospital.management.forms.UserForm;
import com.hospital.management.Hospital.management.repository.AttendenceRepository;
import com.hospital.management.Hospital.management.repository.PatientRepository;
import com.hospital.management.Hospital.management.repository.UserRepository;
import com.hospital.management.Hospital.management.view.LeaveView;
import com.hospital.management.Hospital.management.view.UserView;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
class UserServiceImplTest {

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;

    @MockBean
    private UserRepository repository;
    PasswordEncoder passwordEncoder;

    @MockBean
    private PatientRepository patientRepository;
    @Autowired
    @InjectMocks
    private  PatientServiceimpl patientService;
    @MockBean
    private AttendenceRepository attendenceRepository;


    @Test
    void addUser() {

        UserForm userForm= new UserForm();
        userForm.setEducation("MBBS");
        userForm.setEmail("hari123@gmail.com");
        userForm.setPassword("hari123");
        userForm.setSpeciality("ortho");
        userForm.setName("hari");
        userForm.setType("doctor");
        userForm.setPhoneNumber("9656761548");
        User user = new User(userForm);
        user.setId(1);
        doReturn(user).when(repository).save(any());
        UserView userView= new UserView(user);
//        System.out.println(userForm.getName());
//        System.out.println(userView.getName());
        assertEquals(userView.getEmail(),userService.addUser(userForm).getEmail());
   }
    @Test
    void delete() {

        Integer userId = 1;
        User user = new User("user2", "pen@gmail.com", "7025315412","hari@123","doctor","ortho","MBBS");
        user.setId(1);
        userService.delete(userId);
        verify(repository).findById(userId);
//        System.out.println(verify(repository).findById(userId));
    }

    @Test
    void list() {
        User user1 = new User("user1", "user1@gmail.com", "7025315412","hari@123","doctor","ortho","MBBS");
        User user2=new User("user2", "user2@gmail.com", "7025414578","hari@321","doctor","dentel","MBBS");
        User user = new User();
        Collection<User> expectedDisplay = new ArrayList<>();
        expectedDisplay.add(user1);
        expectedDisplay.add(user2);
        when(repository.findAll()).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay.size(),userService.list().size());

    }

    @Test
    void login() {

    }

    @Test
    void refresh() {
    }

    @Test
    void edit() {
        UserForm userForm=new UserForm();
        Integer Id=1;
        userForm.setName("hari");
        userForm.setEmail("hari123@gmail.com");
        userForm.setPhoneNumber("9656761548");
        userForm.setPassword("hari123");
        userForm.setType("doctor");
        userForm.setSpeciality("ortho");
        userForm.setEducation("MBBS");
        User user= new User("user1", "user1@gmail.com", "7025315412","hari@123","doctor","ortho","MBBS");
        user.setId(1);
        doReturn(user).when(repository).findById(1);
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setSpeciality(userForm.getSpeciality());
        user.setEducation(userForm.getEducation());
        doReturn(user).when(repository).save(user);
        assertEquals(user.getName(),userService.edit(userForm,1).getName());
//        System.out.println(user.getName());
//        System.out.println(userForm.getName());
    }
    @Test
    void patientList() {
        PatientForm patientForm=new PatientForm();
        patientForm.setPatientName("hari");
        patientForm.setGender("male");
        patientForm.setEmail("hari@gmail.com");
        patientForm.setPhoneNumber("9656761548");
        patientForm.setDepartment("ortho");
        patientForm.setDoctorId(12);
        patientForm.setAppointmentDate("12/6/2022");
        patientForm.setTime("10AM");
        Patient patient1=new Patient(patientForm);
        patientForm.setPatientName("jaishankar");
        patientForm.setGender("male");
        patientForm.setEmail("jaishankar@gmail.com");
        patientForm.setPhoneNumber("8606779682");
        patientForm.setDepartment("ortho");
        patientForm.setDoctorId(13);
        patientForm.setAppointmentDate("12/7/2022");
        patientForm.setTime("11AM");
        Patient patient2=new Patient(patientForm);
        Collection<Patient> expectedDisplay=new ArrayList<>();
        expectedDisplay.add(patient1);
        expectedDisplay.add(patient2);
        when(patientRepository.findByDoctorIdByAppointmentDate(patient1.getDoctorId(),patient1.getAppointmentDate())).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay.size(),userService.patientList(patient1.getDoctorId(),patient1.getAppointmentDate()).size());
//        System.out.println(expectedDisplay.size());
//        System.out.println(userService.patientList(patient2.getDoctorId(),patient2.getAppointmentDate()).size());
    }

    @Test
    void doctorDetails() {
        User user = new User("user2", "pen@gmail.com", "7025315412","hari@123","doctor","ortho","MBBS");
        user.setId(2);
        Collection<User> expectedDisplay=new ArrayList<>();
        expectedDisplay.add(user);
        when(repository.findByName("user2")).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay,userService.doctorDetails(user.getName()));
        System.out.println(expectedDisplay);
        System.out.println(userService.doctorDetails(user.getName()));

    }

    @Test
    void leaveReq() {
        LeaveForm leaveForm=new LeaveForm();
        leaveForm.setLeaveType("casual");
        leaveForm.setDate("13/05/2022");
        leaveForm.setDuration("full day");
        leaveForm.setDescription("fever");
        leaveForm.setId(14);
        Attendence attendence=new Attendence();
        attendence.setLeaveType(leaveForm.getLeaveType());
        attendence.setDate(leaveForm.getDate());
        attendence.setDuration(leaveForm.getDuration());
        attendence.setDescription(leaveForm.getDescription());
        attendence.setAttendenceId(1);
        attendence.setStatus((byte) 1);
        attendence.setUser(new User(leaveForm.getId()));
        doReturn(attendence).when(attendenceRepository).save(any());
//        System.out.println(attendence.getLeaveType());
        LeaveView leaveView=new LeaveView(attendence);
//        System.out.println(leaveView.getLeaveType());
        assertEquals(leaveView.getLeaveType(),userService.leaveReq(leaveForm).getLeaveType());
    }

    @Test
    void leaveList() {
        LeaveForm leaveForm=new LeaveForm();
        leaveForm.setLeaveType("casual");
        leaveForm.setDate("13/05/2022");
        leaveForm.setDuration("full day");
        leaveForm.setDescription("fever");
        leaveForm.setId(14);
        Attendence attendence=new Attendence(leaveForm);
        Collection<Attendence> expectedDisplay = new ArrayList<>();
        expectedDisplay.add(attendence);
        when(attendenceRepository.findByStatus()).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay.size(),userService.leaveList().size());
    }

    @Test
    void updateStatus() {
        LeaveForm leaveForm=new LeaveForm();
        leaveForm.setLeaveType("casual");
        leaveForm.setDate("13/05/2022");
        leaveForm.setDuration("full day");
        leaveForm.setDescription("fever");
        leaveForm.setId(14);
        Attendence attendence=new Attendence(leaveForm);
        attendence.setAttendenceId(1);
        when(attendenceRepository.findByAttendenceIdAndStatus(attendence.getAttendenceId(),Attendence.Status.PENDING.value)).thenReturn(attendence);
        attendence.setStatus(Attendence.Status.ABSENT.value);
        when(attendenceRepository.save(any())).thenReturn(attendence);
        LeaveView leaveView=new LeaveView(attendence);
        System.out.println(leaveView.getStatus());
        System.out.println(userService.updateStatus(1).getStatus());
        assertEquals(leaveView.getStatus(),userService.updateStatus(attendence.getAttendenceId()).getStatus());
    }

    @Test
    void approvedLeaves() {
        LeaveForm leaveForm=new LeaveForm();
        leaveForm.setLeaveType("casual");
        leaveForm.setDate("13/05/2022");
        leaveForm.setDuration("full day");
        leaveForm.setDescription("fever");
        leaveForm.setId(14);
        Attendence attendence=new Attendence(leaveForm);
        attendence.setAttendenceId(0);
        when(attendenceRepository.save(any())).thenReturn(attendence);
        attendenceRepository.findByStatus(Attendence.Status.ABSENT.value);
        Collection<Attendence> expectedDisplay = new ArrayList<>();
        expectedDisplay.add(attendence);
        when(attendenceRepository.findByStatus(Attendence.Status.ABSENT.value)).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay.size(),userService.approvedLeaves().size());
    }

    @Test
    void leaveStatus() {
        LeaveForm leaveForm=new LeaveForm();
        leaveForm.setLeaveType("casual");
        leaveForm.setDate("13/05/2022");
        leaveForm.setDuration("full day");
        leaveForm.setDescription("fever");
        leaveForm.setId(14);
        Attendence attendence=new Attendence(leaveForm);
        attendence.setAttendenceId(0);
        attendence.setStatus((byte) 1);
        when(attendenceRepository.save(any())).thenReturn(attendence);
        System.out.println();
        Collection<Attendence> expectedDisplay = new ArrayList<>();
        expectedDisplay.add(attendence);
        when(attendenceRepository.findByStatusAndDoctorId(leaveForm.getId())).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay.size(),userService.leaveStatus(14).size());
    }

    @Test
    void updateStatusToReject() {
        LeaveForm leaveForm=new LeaveForm();
        leaveForm.setLeaveType("casual");
        leaveForm.setDate("13/05/2022");
        leaveForm.setDuration("full day");
        leaveForm.setDescription("fever");
        leaveForm.setId(14);
        Attendence attendence=new Attendence(leaveForm);
        attendence.setAttendenceId(1);
        when(attendenceRepository.findByAttendenceIdAndStatus(attendence.getAttendenceId(),Attendence.Status.PENDING.value)).thenReturn(attendence);
        attendence.setStatus(Attendence.Status.REJECT.value);
        when(attendenceRepository.save(any())).thenReturn(attendence);
        LeaveView leaveView=new LeaveView(attendence);
        assertEquals(leaveView.getStatus(),userService.updateStatusToReject(attendence.getAttendenceId()).getStatus());
    }
}