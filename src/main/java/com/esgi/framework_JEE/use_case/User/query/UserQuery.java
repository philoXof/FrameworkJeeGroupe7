package com.esgi.framework_JEE.use_case.User.query;

import com.esgi.framework_JEE.use_case.User.Domain.entities.User;
import com.esgi.framework_JEE.use_case.User.Domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQuery {

    @Autowired
    UserRepository userRepository;

    public UserQuery(){}

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public User getById(int userId) {
        return userRepository.findById(userId);
    }

    public Boolean userEmailExist(String mail){
        return userRepository.findByEmail(mail) != null;
    }

    public User getByEmailAndPassword(String email, String password){
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
