package com.hospital.management.Hospital.management.controller;

import com.hospital.management.Hospital.management.entity.Attendence;
import com.hospital.management.Hospital.management.entity.Patient;
import com.hospital.management.Hospital.management.entity.User;
import com.hospital.management.Hospital.management.forms.LeaveForm;
import com.hospital.management.Hospital.management.forms.UserForm;
import com.hospital.management.Hospital.management.service.UserService;
import com.hospital.management.Hospital.management.view.LeaveView;
import com.hospital.management.Hospital.management.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/user")
public class Usercontroller {


  @Autowired
  UserService userService;

  @PostMapping("/add")
  public UserView addUser(@RequestBody UserForm userform) {
    return userService.addUser( userform );
  }

  @DeleteMapping("/delete/{userId}")
  public void delete(@PathVariable("userId") Integer userId) {
    userService.delete( userId );
  }

  @GetMapping("/list")
  public Collection<User> list() {
    return userService.list();
  }

  @PutMapping("/edit/{userId}")
  public UserView edit(@RequestBody UserForm userForm, @PathVariable Integer userId) {
    return userService.edit(userForm, userId);
  }

  @GetMapping("/patientList/{id}")

         public Collection<Patient> patientList(@PathVariable Integer id ) {
    System.out.println(id);
    Date localdate= Calendar.getInstance().getTime();
    String dateformat="YYYY-MM-dd";
    DateFormat currrentDate=new SimpleDateFormat(dateformat);
    String formattedDate=currrentDate.format(localdate);
    System.out.println("HELLO"+formattedDate);
      return userService.patientList(id,formattedDate);

    }
  @GetMapping("/findDoctor/{name}")
  public Collection<User>doctorDetails(@PathVariable String name){
    return userService.doctorDetails(name);
  }

  @PostMapping("/leaveRequest")
  public LeaveView leaveReq(@RequestBody LeaveForm leaveForm)
  {
    return userService.leaveReq(leaveForm);
  }

 @GetMapping("/viewLeave")
  public Collection<Attendence>leaveList()
 {
   return  userService.leaveList();
 }

 @GetMapping("/approveLeave/{attendenceId}")
  public  LeaveView updateStatus(@PathVariable Integer attendenceId)
 {
   return userService.updateStatus(attendenceId);
 }

 @GetMapping("/approvedLeaves")
  public Collection<Attendence>approvedLeaves()

 {
   return  userService.approvedLeaves();
 }
 @GetMapping("leaveStatus/{doctorId}")
    public Collection<Attendence>leaveStatus(@PathVariable Integer doctorId)
 {
     return userService.leaveStatus(doctorId);
 }

 @GetMapping("/rejectLeave/{attendenceId}")
    public LeaveView updateStatusToReject(@PathVariable Integer attendenceId)
 {
     return  userService.updateStatusToReject(attendenceId);
 }

 @GetMapping("/findEmail/{data}")
 public User findEmail(@PathVariable String data)
 {
     return  userService.findEmail(data);
 }






}






