package com.ms.user.controller;

import com.ms.user.dto.UserRecord;
import com.ms.user.model.UserModel;
import com.ms.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecord userRecord){
        var user = new UserModel();
        BeanUtils.copyProperties(userRecord,user);
       return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
