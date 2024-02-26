package com.example.live.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique=true, name = "number")
  private String number;

  @Column(name = "position")
  private String position;

  @Column(name = "name")
  private String name;

  //must be 12 digits
  @Pattern(regexp = "\\d{12}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")
  private String phone;

  @Email
  private String email;

  //getters and setters

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
