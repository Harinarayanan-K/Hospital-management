package com.hospital.management.Hospital.management.repository;

import com.hospital.management.Hospital.management.entity.Attendence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;

public interface AttendenceRepository extends Repository<Attendence,Integer> {

Attendence save(Attendence attendence);
    @Query("select i from Attendence i left join fetch i.user  where i.status=1 ")
    Collection<Attendence> findByStatus();

    Attendence findByAttendenceIdAndStatus(Integer attendenceId,byte status);

    Collection<Attendence> findByStatus(byte status);

    @Query("select i from Attendence i  where i.user.id=?1")
    Collection<Attendence> findByStatusAndDoctorId(Integer doctorId);
     @Query(value ="select duration from attendence where date=?1 and id=?2",nativeQuery = true)
    String findDurationByAppointmentDateByid(String date, Integer id);



//    LeaveView<Attendence> updateStatusToreject(Integer attendenceId);
}