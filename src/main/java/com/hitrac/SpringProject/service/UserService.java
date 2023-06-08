package com.hitrac.SpringProject.service;


import com.hitrac.SpringProject.dto.Response;
import com.hitrac.SpringProject.model.UserDtls;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;


@Service
public interface UserService {
    ResponseEntity<String> register(UserDtls user);

    ResponseEntity<Response> login(UserDtls userDtls);

    public boolean checkEmail(String email);

}
