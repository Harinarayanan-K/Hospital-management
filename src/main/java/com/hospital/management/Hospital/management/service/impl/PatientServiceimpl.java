package com.hospital.management.Hospital.management.service.impl;


import com.hospital.management.Hospital.management.entity.Patient;
import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.forms.PatientForm;
import com.hospital.management.Hospital.management.repository.PatientRepository;
import com.hospital.management.Hospital.management.repository.UserRepository;
import com.hospital.management.Hospital.management.service.PatientService;
import com.hospital.management.Hospital.management.view.PatientView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceimpl implements PatientService {
@Autowired
private PatientRepository patientRepository;

    @Autowired
    private UserRepository
            userRepository;

    @Override
    public PatientView patientBook(PatientForm patientForm) {
        System.out.println(patientForm.getPatientName());
    return new PatientView (patientRepository.save(new Patient(patientForm.getPatientName(),patientForm.getGender(),patientForm.getEmail(),patientForm.getPhoneNumber(),patientForm.getDepartment(),patientForm.getDoctorId(),patientForm.getAppointmentDate(),patientForm.getTime())));
//        patientRepository.save(new Patient(patientForm));
    }

    @Override
    public Collection<String> listDepartments() {
        return  userRepository.findAllDepartment();

    }

    @Override
    public Collection<User> listDoctors(String department) {
        return userRepository.findByDepartment(department);
    }


    @Override
    public Collection<String> listTime(String dates,Integer doctorId) {
        return patientRepository.findByAppointmentDateByDoctorId(dates,doctorId);
    }

    @Override
    public Collection<PatientView> patientHistory(String userdata) {
        List<Patient> patientList = patientRepository.findByEmail(userdata);

        List<PatientView> patientViewList = new ArrayList<>();
        return patientList.stream().map(p-> new PatientView(p)).collect(Collectors.toList());
    }

}
