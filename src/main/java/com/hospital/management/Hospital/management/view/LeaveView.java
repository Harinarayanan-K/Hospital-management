package com.hospital.management.Hospital.management.view;

import com.hospital.management.Hospital.management.entity.Attendence;

public class LeaveView {
    private  Integer attendenceId;

    private String date;

    private  String duration;
    private String  leaveType;

    private Integer status;

    private  String description;

    private Integer user;

    public Integer getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(Integer attendenceId) {
        this.attendenceId = attendenceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    private String userName;


    public LeaveView(Attendence attendence) {
        this.attendenceId= attendence.getAttendenceId();
        this.date = attendence.getDate();
        this.duration=attendence.getDuration();
        this.description= attendence.getDescription();
        this.status = Integer.valueOf(attendence.getStatus());
        this.leaveType=attendence.getLeaveType();
        this.user=attendence.getUser().getId();
        this.userName= attendence.getUser().getName();
        System.out.println(userName);
    }
    public LeaveView(String duration) {

        this.duration=duration;

    }
}
