package com.akilesh.socialmedia.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.akilesh.socialmedia.entity.Users;
import com.akilesh.socialmedia.model.requestModel.UserRequestModel;
import com.akilesh.socialmedia.repository.UsersRepository;

/**
 * @author AkileshVasudevan
 */
@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Long registerUser(UserRequestModel users){
        Users user = new Users();
        user.setName(users.getName());
        user.setPassword(passwordEncoder.encode(users.getPassword()));
        user.setEmail(users.getEmail());
        user.setRole("USER");
        user.setEnabled(true);
        return usersRepository.save(user).getId();
    }


}
