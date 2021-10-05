
package com.endlos.admin.user.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.endlos.admin.user.Repository.PagebleResponseRepository;
import com.endlos.admin.user.Repository.RoleRepository;
import com.endlos.admin.user.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.endlos.admin.jwt.JwtUtils;
import com.endlos.admin.user.model.ERole;
import com.endlos.admin.user.model.Role;
import com.endlos.admin.user.model.User;
import com.endlos.admin.request.LoginRequest;
import com.endlos.admin.request.SignupRequest;
import com.endlos.admin.response.JwtResponse;
import com.endlos.admin.response.MessageResponse;
import com.endlos.admin.security.UserDetailsImpl;

//security.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PagebleResponseRepository pagebleResponseRepository;

    @Autowired
    JwtUtils jwtUtils;
    private final Path root = Paths.get("uploads");

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
                userDetails.getEmail(), userDetails.getPhoneno(), userDetails.getAddress(), userDetails.getStatus(),
                userDetails.getDatetime(), roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws Exception {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account encoder.encode(signUpRequest.getPassword())
        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()), signUpRequest.getAddress()
                , signUpRequest.getPhoneno(),
                signUpRequest.getStatus());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER);
            // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
                        // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_SUPERUSER);
                        // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER);
                        // .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @RequestMapping(value = "/{status}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> FindByStatus(@PathVariable int status,
                                                            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size) {
        try {
            List<User> user = new ArrayList<User>();
            org.springframework.data.domain.Pageable paging = PageRequest.of(page, size);

            Page<User> pageTuts = pagebleResponseRepository.findBystatus(status, paging);
            user = pageTuts.getContent();

            Map<String, Object> response = new HashMap<String, Object>();
            response.put("Total User", user);
            response.put("current Page", pageTuts.getNumber());
            int d = pageTuts.getNumber();
            System.out.println(d);
            response.put("total Items", pageTuts.getTotalElements());
            response.put("total Pages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> FindAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        try {
            List<User> user = new ArrayList<User>();
            org.springframework.data.domain.Pageable paging = PageRequest.of(page, size);

            Page<User> pageTuts = userRepository.findAll(paging);
            user = pageTuts.getContent();

            Map<String, Object> response = new HashMap<String, Object>();
            response.put("Total User", user);
            response.put("current Page", pageTuts.getNumber());
            int d = pageTuts.getNumber();
            System.out.println(d);
            response.put("total Items", pageTuts.getTotalElements());
            response.put("total Pages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "findbyid/{id}", method = RequestMethod.GET)
    public Optional<User> FindById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @GetMapping(value = "/status/{status}")
    public ResponseEntity<List<User>> findbystatus(@PathVariable int status) {
        return ResponseEntity.ok(userRepository.findBystatus(status));
    }


    @RequestMapping(value = "deleteby/{id}", method = RequestMethod.DELETE)
    public void UserDeleteById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateTutorial(@PathVariable("id") long id, @RequestBody User tutorial) {
        Optional<User> tutorialData = userRepository.findById(id);

        if (tutorialData.isPresent()) {
            User user = tutorialData.get();
//	    	user.setPassword(encoder.encode(tutorial.getPassword()));
            user.setEmail(tutorial.getEmail());
            user.setPhoneno(tutorial.getPhoneno());
//	    	user.setPin(tutorial.getPin());
            user.setAddress(tutorial.getAddress());
            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<User> updatepassword(@PathVariable("id") long id, @RequestBody User tutorial) {
        Optional<User> tutorialData = userRepository.findById(id);

        if (tutorialData.isPresent()) {
            User user = tutorialData.get();
            user.setPassword(encoder.encode(tutorial.getPassword()));

            return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/findbyuser")
    public ResponseEntity<User> findByuser(@RequestParam String username) {
        return ResponseEntity.ok(userRepository.findByUser(username));
    }
}
