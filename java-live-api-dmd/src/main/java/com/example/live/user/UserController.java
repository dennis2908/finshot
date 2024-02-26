package com.example.live.user;

import com.example.live.function.ResponseHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  private ResponseHandler responseHandler;

  @GetMapping
  // public List<User> getAllUsers() {
  //   return userRepository.findAll();
  // }
  ResponseEntity<Object> getAllCountry() {
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      userRepository.findAll()
    );
  }

  @GetMapping("/{id}")
  // public User getUserById(@PathVariable Long id) {
  //   return userRepository.findById(id).get();
  // }
  ResponseEntity<Object> getUserById(@PathVariable Long id) {
    // User getUser = userRepository.findUserByName("dennis");
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      userRepository.findById(id).get()
    );
  }

  @GetMapping("/findUserByName")
  // public User getUserById(@PathVariable Long id) {
  //   return userRepository.findById(id).get();
  // }
  ResponseEntity<Object> findUserByName(@RequestParam String name) {
    // System.out.println("ddddd",userRepository.findUserByName(name));
    if(name!= null && !name.trim().isEmpty()){
      return ResponseHandler.generateResponse(
        HttpStatus.OK,
        true,
        "Success",
        userRepository.findUserByName(name)
      );
    }
    else{
      return ResponseHandler.generateResponse(
        HttpStatus.OK,
        true,
        "Success",
        userRepository.findAll()
      );
    }
  }

  @PostMapping
  // public User createUser(@RequestBody User user) {
  //   return userRepository.save(user);
  // }
  ResponseEntity<Object> createUser(@RequestBody User user) {
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      userRepository.save(user)
    );
  }

  @PutMapping("/{id}")
  // public User updateUser(@PathVariable Long id, @RequestBody User user) {
  //   User existingUser = userRepository.findById(id).get();
  //   existingUser.setName(user.getName());
  //   existingUser.setEmail(user.getEmail());
  //   existingUser.setAddress(user.getAddress());
  //   return userRepository.save(existingUser);
  // }
  ResponseEntity<Object> updateUser(
    @PathVariable Long id,
    @RequestBody User user
  ) {
    User existingUser = userRepository.findById(id).get();
    existingUser.setNumber(user.getNumber());
    existingUser.setPosition(user.getPosition());
    existingUser.setName(user.getName());
    existingUser.setPhone(user.getPhone());
    existingUser.setEmail(user.getEmail());
    return ResponseHandler.generateResponse(
      HttpStatus.OK,
      true,
      "Success",
      userRepository.save(existingUser)
    );
  }

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable Long id) {
    try {
      userRepository.findById(id).get();
      userRepository.deleteById(id);
      return "User deleted successfully";
    } catch (Exception e) {
      return "User not found";
    }
  }
}
