package com.ms.user.service;

import com.ms.user.model.UserModel;
import com.ms.user.producer.UserProducer;
import com.ms.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProducer userProducer;

    @Transactional
    public UserModel save(UserModel userModel){
       userModel = userRepository.save(userModel);
       userProducer.publishMessagenEmail(userModel);
       return userModel;
    }
}
