package com.example.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
  
  @Autowired
  public JavaMailSender emailSender;
  
  @ResponseBody
  @RequestMapping("/sendEmail/{email}")
  public String sendEmail(@PathVariable("email") String email) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject("Your leave application");
    //replace with actual content, craft using query from leave_app
    message.setText("Your leave application for the period 28th May 2019 to 30th May 2019 has been recorded. Reason: Sick of school.");
    this.emailSender.send(message);
    return null;
  }
}