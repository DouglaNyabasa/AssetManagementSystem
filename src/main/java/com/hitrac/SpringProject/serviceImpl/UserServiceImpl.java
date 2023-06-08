package com.hitrac.SpringProject.serviceImpl;
import com.hitrac.SpringProject.model.Role;
import com.hitrac.SpringProject.model.UserDtls;
import com.hitrac.SpringProject.repsitory.UserRepository;
import com.hitrac.SpringProject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncode;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncode) {
        this.userRepository = userRepository;
        this.passwordEncode = passwordEncode;
    }

    @Override
    public ResponseEntity register(UserDtls user) {
        Optional<UserDtls> username = userRepository.findByUsername(user.getUsername());
        if(username.isPresent()){
            return ResponseEntity.ok().body("user already exists");
        }
        UserDtls user1 = new UserDtls();
        user1.setUsername(user.getUsername());
        user1.setPassword(passwordEncode.encode(user.getPassword()));
        user.setRole(String.valueOf(Role.valueOf("ROLE_USER")));
        user1.setLastname(user.getLastname());
        UserDtls saved = userRepository.save(user1);
        return ResponseEntity.ok().body(saved);
    }
    @Override
    public ResponseEntity login(UserDtls userDtls) {
        userDtls.getPassword();

        Optional<UserDtls> user = userRepository.findByUsername(userDtls.getUsername());

       if(user.isPresent()){
           if(user.get().getPassword().equals(userDtls.getPassword())){
               return  ResponseEntity.ok().body("log in successful");
           }
       }
       return  ResponseEntity.ok().body("wrong user details");
    }

    @Override
    public boolean checkEmail(String email) {
        return  userRepository.existsByEmail(email);
    }

}
