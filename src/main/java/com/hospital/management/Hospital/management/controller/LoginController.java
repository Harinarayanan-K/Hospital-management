package com.hospital.management.Hospital.management.controller;

import com.hospital.management.Hospital.management.forms.LoginForm;
import com.hospital.management.Hospital.management.service.UserService;
import com.hospital.management.Hospital.management.view.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")

public class LoginController {
  @Autowired
  private UserService userService;
  @PostMapping
  public LoginView login(@Valid @RequestBody LoginForm form, Errors errors) {
    return userService.login(form,errors);
  }

}
