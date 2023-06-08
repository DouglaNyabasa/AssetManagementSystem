package com.hitrac.SpringProject.controller;
import com.hitrac.SpringProject.dto.Response;
import com.hitrac.SpringProject.model.UserDtls;
import com.hitrac.SpringProject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class LoginRegisterController {

    private final UserService userService;

    public LoginRegisterController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public String register( @ModelAttribute UserDtls user, HttpSession session){
        boolean f = userService.checkEmail(user.getEmail());
        if (f) {
            session.setAttribute("msg", "Email Id already exists");
        }

        else {
            ResponseEntity userDtls = userService.register(user);
            if (userDtls != null) {
                session.setAttribute("msg", "Register Successfully");
            } else {
                session.setAttribute("msg", "Something wrong on server");
            }
        }

        return "redirect:/register";
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@ModelAttribute UserDtls user){
        return  userService.login(user);
    }

}
