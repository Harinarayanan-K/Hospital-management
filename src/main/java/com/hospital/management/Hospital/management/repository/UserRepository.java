package com.hospital.management.Hospital.management.repository;

import com.hospital.management.Hospital.management.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository extends Repository<User,Integer> {



  User save(User user);

  User findById(Integer userId);

  void delete(User userId);

  @Query("select i from User i where i.type='doctor'")
  Collection<User> findAll();

  Collection<User> findByName(String name);

  Optional<User> findByEmail(String email);

  Optional<User> findByIdAndPassword(int userId, String password);

//  select name from user where not id in (select id from attendence where date="2022-06-21") and department="ortho";


  @Query("SELECT distinct i.speciality from User i where i.type='doctor'")
  Collection<String> findAllDepartment();



  @Query(value = "select * from user where  id not in (select id from attendence where date = ?2 and status=0 and duration='full day') and department = ?1",nativeQuery = true)
  Collection<User> findByDepartment(String departments,String date);

  User findEmailByEmail(String emailData);




}
