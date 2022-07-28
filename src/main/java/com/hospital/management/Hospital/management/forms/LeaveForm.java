package com.hospital.management.Hospital.management.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LeaveForm {
    @NotBlank
    private String leaveType;
    private String date;

    private  String duration;
    @NotBlank
    private  Integer status;
    @NotBlank
    private  String description;
    @NotNull
    private  Integer id;

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
