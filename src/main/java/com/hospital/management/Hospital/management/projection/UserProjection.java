package com.hospital.management.Hospital.management.projection;

import org.springframework.beans.factory.annotation.Value;

public interface UserProjection {
    @Value("#{target.id}")
    int getId();
    @Value("#{target.type}")
    String getType();
    @Value("#{target.speciality}")
    String getSpeciality();
    @Value("#{education}")
    String getEducation();
}
