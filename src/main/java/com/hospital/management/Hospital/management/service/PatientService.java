package com.hospital.management.Hospital.management.service;

import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.forms.PatientForm;
import com.hospital.management.Hospital.management.view.LeaveView;
import com.hospital.management.Hospital.management.view.PatientView;

import java.util.Collection;

public interface PatientService {
     PatientView patientBook(PatientForm patientForm);

    Collection<String> listDepartments();


    Collection<User> listDoctors(String department, String date);



    Collection<String> listTime(String dates,Integer doctorId);

    Collection<PatientView> patientHistory(String userdata);

    LeaveView getDuration(String date, Integer id);
}
