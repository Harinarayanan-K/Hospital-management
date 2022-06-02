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

  Optional<User> findByUserIdAndPassword(int userId, String password);

  @Query("SELECT distinct i.speciality from User i where i.type='doctor'")
  Collection<String> findAllDepartment();



  @Query("SELECT i from User i where i.speciality=?1")
  Collection<User> findByDepartment(String departments);



}
