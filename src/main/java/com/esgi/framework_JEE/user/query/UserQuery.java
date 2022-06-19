package com.esgi.framework_JEE.user.query;

import com.esgi.framework_JEE.user.Domain.entities.User;
import com.esgi.framework_JEE.user.Domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQuery {

    final
    UserRepository userRepository;

    public UserQuery(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public User getById(int userId) {
        return userRepository.findById(userId);
    }

    public Boolean userEmailExist(String mail){
        return userRepository.findByEmail(mail) != null;
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User getByEmailAndPassword(String email, String password){
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
