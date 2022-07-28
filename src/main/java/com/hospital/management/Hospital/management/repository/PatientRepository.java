package com.hospital.management.Hospital.management.repository;

import com.hospital.management.Hospital.management.entity.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.List;

public interface PatientRepository extends Repository<Patient,Integer> {


     String findByTime(String time);

    Patient save(Patient patient);
    @Query ("select i.time from Patient i where i.appointmentDate=?1 and i.doctorId=?2")
    Collection<String> findByAppointmentDateByDoctorId(String dates,Integer doctorId);
    @Query("select i from Patient i where i.doctorId=?1 and  i.appointmentDate=?2")
    Collection<Patient> findByDoctorIdByAppointmentDate(Integer id, String localDate);
    @Query("select i from Patient i left join fetch i.user  where i.email=?1 or  i.phoneNumber=?1")
    List<Patient> findByEmail(String userdata);


}
