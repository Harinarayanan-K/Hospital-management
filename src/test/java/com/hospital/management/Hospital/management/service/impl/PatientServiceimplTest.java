package com.hospital.management.Hospital.management.service.impl;

import com.hospital.management.Hospital.management.entity.Attendence;
import com.hospital.management.Hospital.management.entity.Patient;
import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.forms.LeaveForm;
import com.hospital.management.Hospital.management.forms.PatientForm;
import com.hospital.management.Hospital.management.repository.AttendenceRepository;
import com.hospital.management.Hospital.management.repository.PatientRepository;
import com.hospital.management.Hospital.management.repository.UserRepository;
import com.hospital.management.Hospital.management.view.LeaveView;
import com.hospital.management.Hospital.management.view.PatientView;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
class PatientServiceimplTest {

    @Autowired
    @InjectMocks
    private PatientServiceimpl patientServiceimpl;
    @MockBean
    private PatientRepository patientRepository;
    @MockBean
    private  AttendenceRepository attendenceRepository;
    @MockBean
    private UserRepository userRepository;



    @Test
    void patientBook() {
        PatientForm patientForm=new PatientForm();
        patientForm.setPatientName("user");
        patientForm.setGender("male");
        patientForm.setEmail("user@gnmail.com");
        patientForm.setPhoneNumber("9656764514");
        patientForm.setDepartment("ortho");
        patientForm.setDoctorId(2);
        patientForm.setAppointmentDate("12/06/2022");
        patientForm.setTime("12AM");
        Patient patient=new Patient(patientForm);
        int patientId=11;
        patient.setPatientId(patientId);
        User user = new User("user2", "pen@gmail.com", "7025315412","hari@123","doctor","ortho","MBBS");
        user.setId(2);
        user.setName("user2");
        patient.setUser(user);
        patient.setPatientName(patientForm.getPatientName());
        patient.setGender(patientForm.getGender());
        patient.setEmail(patientForm.getEmail());
        patient.setPhoneNumber(patientForm.getPhoneNumber());
        patient.setDepartment(patientForm.getDepartment());
        patient.setDoctorId(patientForm.getDoctorId());
        patient.setAppointmentDate(patientForm.getAppointmentDate());
        patient.setTime(patientForm.getTime());
        System.out.println( patient.getUser().getName());
        when(patientRepository.save(any())).thenReturn(patient);
        PatientView patientView=new PatientView(patient);
        System.out.println("hello"+patientView.getPatientName());
        System.out.println("hello"+ patientServiceimpl.patientBook(patientForm).getPatientName());
        assertEquals(patientView.getPatientName(),patientServiceimpl.patientBook(patientForm).getPatientName());

    }
    
    @Test
    void listDepartments() {
        User user1 = new User("user1", "user1@gmail.com", "7025315412","hari@123","doctor","ortho","MBBS");
        User user2 = new User("user2", "user2@gmail.com", "7025315412","user@123","doctor","cardio","MBBS");
        Collection<String> expectedDisplay=new ArrayList<>();
        expectedDisplay.add(String.valueOf(user1.getSpeciality()));
        expectedDisplay.add(String.valueOf(user2.getSpeciality()));
        when(userRepository.findAllDepartment()).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay,patientServiceimpl.listDepartments());
        System.out.println(expectedDisplay);
        System.out.println(patientServiceimpl.listDepartments());
    }

    @Test
    void listDoctors() {
        User user1 = new User("user1", "user1@gmail.com", "7025315412","hari@123","doctor","ortho","MBBS");
        user1.setId(1);
        Collection<User> expectedDisplay=new ArrayList<>();
        expectedDisplay.add(user1);
        when(userRepository.findByDepartment("ortho","12/10/2022")).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay,patientServiceimpl.listDoctors("ortho","12/10/2022"));

    }
    @Test
    void listTime() {
        PatientForm patientForm=new PatientForm();
        patientForm.setPatientName("user");
        patientForm.setGender("male");
        patientForm.setEmail("user@gnmail.com");
        patientForm.setPhoneNumber("9656764514");
        patientForm.setDepartment("ortho");
        patientForm.setDoctorId(2);
        patientForm.setAppointmentDate("12/06/2022");
        patientForm.setTime("12AM");
        Patient patient=new Patient(patientForm);
        int patientId=11;
        patient.setPatientId(patientId);
        PatientForm patientForm2=new PatientForm();
        patientForm2.setPatientName("user");
        patientForm2.setGender("male");
        patientForm2.setEmail("user@gnmail.com");
        patientForm2.setPhoneNumber("9656764514");
        patientForm2.setDepartment("ortho");
        patientForm2.setDoctorId(2);
        patientForm2.setAppointmentDate("12/06/2022");
        patientForm2.setTime("2AM");
        Patient patient2=new Patient(patientForm2);
        int patientId2=11;
        patient2.setPatientId(patientId);
        Collection<String> expectedDisplay=new ArrayList<>();
        expectedDisplay.add(String.valueOf(patient.getTime()));
        expectedDisplay.add(String.valueOf(patient2.getTime()));
        System.out.println(expectedDisplay);
        when(patientRepository.findByAppointmentDateByDoctorId(patientForm.getAppointmentDate(),patientForm.getDoctorId())).thenReturn(expectedDisplay);
        assertEquals(expectedDisplay,patientServiceimpl.listTime(patientForm.getAppointmentDate(),patientForm.getDoctorId()));
    }
    @Test
    void patientHistory() {
        User user1 = new User("user1", "user1@gmail.com", "7025315412","hari@123","doctor","ortho","MBBS");
        user1.setId(2);
        PatientForm patientForm= new PatientForm();
        patientForm.setPatientName("user");
        patientForm.setGender("male");
        patientForm.setEmail("user@gmail.com");
        patientForm.setPhoneNumber("9656764514");
        patientForm.setDepartment("ortho");
        patientForm.setDoctorId(2);
        patientForm.setAppointmentDate("12/06/2022");
        patientForm.setTime("12AM");
        Patient patient = new Patient(patientForm);
        PatientForm patientForm2= new PatientForm();
        patientForm2.setPatientName("user");
        patientForm2.setGender("male");
        patientForm2.setEmail("hari@gmail.com");
        patientForm2.setPhoneNumber("9656764518");
        patientForm2.setDepartment("ortho");
        patientForm2.setDoctorId(2);
        patientForm2.setAppointmentDate("12/06/2022");
        patientForm2.setTime("12AM");
        Patient patient2 = new Patient(patientForm);
        List<Patient> list=new ArrayList<Patient>();
        list.add(patient);
        when(patientRepository.findByEmail(patientForm.getEmail())).thenReturn((list));
        PatientView patientView=new PatientView(patient);
        System.out.println("pppppppp"+patientView.getPatientName());
        Collection<PatientView> expectedDisplay=new ArrayList<>();
        expectedDisplay.add((patientView));
        assertEquals(expectedDisplay.size(),patientServiceimpl.patientHistory("user@gmail.com").size());
    }

    @Test
    void getDuration() {
        LeaveForm leaveForm=new LeaveForm();
        leaveForm.setLeaveType("casual");
        leaveForm.setDate("13/05/2022");
        leaveForm.setDuration("full day");
        leaveForm.setDescription("fever");
        leaveForm.setId(14);
        Attendence attendence=new Attendence(leaveForm);
        attendence.setAttendenceId(1);
        String leave="";
        LeaveView leaveView=new LeaveView(attendence);
        when(attendenceRepository.findDurationByAppointmentDateByid(leaveForm.getDate(),leaveForm.getId())).thenReturn(leave);
        System.out.println(attendence.getDuration());
        System.out.println(leaveView.getDuration());
        System.out.println("hello"+patientServiceimpl.getDuration("13/05/2022",14).getDuration());
        assertEquals(leave,patientServiceimpl.getDuration("13/05/2022",14).getDuration());
        }
    }
