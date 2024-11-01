/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ecommerce.controllers;

import com.example.ecommerce.exceptions.UserNotFoundException;
import com.example.ecommerce.models.ERole;
import com.example.ecommerce.models.Role;
import com.example.ecommerce.models.User;
import com.example.ecommerce.payload.request.LoginRequest;
import com.example.ecommerce.payload.request.SignupRequest;
import com.example.ecommerce.payload.response.JwtResponse;
import com.example.ecommerce.payload.response.MessageResponse;
import com.example.ecommerce.payload.response.UserInfoResponse;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.security.jwt.JwtUtils;
import com.example.ecommerce.security.services.UserDetailsImpl;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @GetMapping("/users")
  Page<User> all( @RequestParam(name = "page", defaultValue = "0") int page,
        @RequestParam(name = "size", defaultValue = "4") int size,
        @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
         @RequestParam(name = "sortOrder", defaultValue = "normal") String sortOrder) {
      
     // Sort objectSort=  sortOrder=="asc" ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
      Sort objectSort;
     
              
       if(sortOrder.equals("ascend")){
       // System.out.println("ascend");    
       objectSort=Sort.by(sortBy).ascending();   
      }else if(sortOrder.equals("descend"))
      {
        //    System.out.println("desc");    
       objectSort=Sort.by(sortBy).descending();
      }else {
           // System.out.println("normal");    
        objectSort=Sort.by("id").ascending(); 
      }
      PageRequest sorted = 
      // PageRequest.of(page, size, Sort.by("id"));
              PageRequest.of(page,size, objectSort);
     // PageRequest firstPageWithTwoElements = PageRequest.of(1,4,objectSort);
    return userRepository.findAll(sorted);
  }
  @GetMapping("/user/{id}")
  User one(@PathVariable Long id) { 
    return userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }
  
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager
        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

   // ResponseCookie jwt = jwtUtils.generateJwtCookie(userDetails);
String jwt = jwtUtils.generateJwtToken(authentication);
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    
return ResponseEntity.ok(new JwtResponse(jwt,  userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(), roles));
//final String token = jwtTokenUtil.generateToken(userDetails);

		//return ResponseEntity.ok(new JwtResponse(token));
  /*  return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
        .body(new UserInfoResponse(userDetails.getId(),
                                   userDetails.getUsername(),
                                   userDetails.getEmail(),
                                   roles));*/
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
                         signUpRequest.getEmail(),
                         encoder.encode(signUpRequest.getPassword()));

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
           /* case "user":
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);

          break;*/
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);

          break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

  @PostMapping("/signout")
  public ResponseEntity<?> logoutUser() {
    ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
        .body(new MessageResponse("You've been signed out!"));
  }
}