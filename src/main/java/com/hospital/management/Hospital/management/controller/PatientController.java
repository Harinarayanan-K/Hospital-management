package com.hospital.management.Hospital.management.controller;

import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.forms.PatientForm;
import com.hospital.management.Hospital.management.service.PatientService;
import com.hospital.management.Hospital.management.view.LeaveView;
import com.hospital.management.Hospital.management.view.PatientView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/patient")


public class PatientController {

    @Autowired
     private  PatientService patientService;
    @PostMapping("/book")
    public PatientView bookDoctor(@RequestBody PatientForm patientForm)
    {
       return patientService.patientBook(patientForm);
    }
    @GetMapping("/listDepartment")
     public Collection<String> listDepartment(){
        return patientService.listDepartments();
    }
    @GetMapping("/listDoctors")
    public  Collection<User>listDoctors(@RequestParam String department, @RequestParam String date){
        return patientService.listDoctors(department,date);
    }

    @GetMapping("/listTime")
    public Collection<String>listTime(@RequestParam String dates,@RequestParam Integer doctorId)
    {
        return patientService.listTime(dates,doctorId);
    }
    @GetMapping("/patientHistory/{userdata}")
    public  Collection<PatientView>patientHistory(@PathVariable("userdata") String userdata)
    {
        return  patientService.patientHistory(userdata);
    }
    @GetMapping("/leaveDuration")
    public LeaveView getDuration(@RequestParam String date, @RequestParam Integer id)
    {
        return  patientService.getDuration(date,id);
    }



}
