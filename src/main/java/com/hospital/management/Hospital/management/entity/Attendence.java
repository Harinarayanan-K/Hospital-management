package com.hospital.management.Hospital.management.entity;


import com.hospital.management.Hospital.management.forms.LeaveForm;

import javax.persistence.*;

@Entity
@Table(name ="Attendence")

public class Attendence {



    public static enum Status {
        ABSENT((byte)0),
        PENDING((byte)1),
        HALFDAY((byte)2),
        REJECT((byte)3);

        public   final byte value;
        private  Status(byte value)
        {
            this.value=value;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendence_id")
    private Integer attendenceId;


    @ManyToOne
    private User user;

    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "status")
    private byte status;

    @Column(name = "date")
    private String date;

    @Column(name="duration")
    private String duration;

    @Column(name = "describtion")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public Integer getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(Integer attendenceId) {
        this.attendenceId = attendenceId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public Attendence() {
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
//    public Attendence(String leaveType, Integer status, Integer date, String description) {
//
//        this.leaveType = leaveType;
//        this.status = status;
//        this.date = date;
//        this.description = description;
//    }

    public Attendence(LeaveForm leaveForm) {
        this.user = new User(leaveForm.getId());
        this.leaveType= leaveForm.getLeaveType();
        this.date=leaveForm.getDate();
        this.duration=leaveForm.getDuration();
        this.status=Status.PENDING.value;
        this.description=leaveForm.getDescription();
    }
}
