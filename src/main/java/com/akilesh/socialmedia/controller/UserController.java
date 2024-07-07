package com.akilesh.socialmedia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.akilesh.socialmedia.model.requestModel.UserRequestModel;
import com.akilesh.socialmedia.service.UserService;
import jakarta.validation.Valid;

/**
 * @author AkileshVasudevan
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Long registerUser(@RequestBody @Valid UserRequestModel userData){
        return userService.registerUser(userData);
    }
}
