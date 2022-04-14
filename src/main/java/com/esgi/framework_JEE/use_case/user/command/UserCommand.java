package com.esgi.framework_JEE.use_case.user.command;



import com.esgi.framework_JEE.use_case.user.entities.User;
import com.esgi.framework_JEE.use_case.user.Domain.repository.UserRepository;
import com.esgi.framework_JEE.use_case.user.validation.UserValidationService;
import com.esgi.framework_JEE.use_case.user.web.request.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Command object
 */
@Service
public final class UserCommand {
    @Autowired
    UserRepository userRepository;
    UserValidationService userValidationService = new UserValidationService();


    public User create(UserRequest userRequest){
        var user = new User();
        user.setEmail(userRequest.email);
        user.setFirstname(userRequest.firstname);
        user.setLastname(userRequest.lastname);
        user.setPassword(userRequest.password);
        if (!userValidationService.isUserValid(user)){
            throw new RuntimeException("Invalid user properties");
        }
        return userRepository.save(user);
    }


    public User changePassword(int userId, String password){
        Optional<User> dbUser = Optional.ofNullable(userRepository.findById(userId));
        if(dbUser.isPresent()){
            var user = dbUser.get();
            user.setPassword(password);
            return userRepository.save(user);
        }else
            return null;
    }

    public User changeFirstname(int userId, UserRequest userRequest) {
        Optional<User> userFromDB = Optional.ofNullable(userRepository.findById(userId));
        if(!userFromDB.isEmpty()){
            var user = userFromDB.get();
            user.setFirstname(userRequest.firstname);
            return userRepository.save(user);
        }
        return null;
    }

    public User changeLastname(int userId, UserRequest userRequest) {
        Optional<User> userFromDB = Optional.ofNullable(userRepository.findById(userId));
        if(!userFromDB.isEmpty()){
            var user = userFromDB.get();
            user.setLastname(userRequest.lastname);
            return userRepository.save(user);
        }
        return null;
    }

    public User changeEmail(int userId, UserRequest userRequest){
        Optional<User> userFromDB = Optional.ofNullable(userRepository.findById(userId));
        if(!userFromDB.isEmpty()){
            var user = userFromDB.get();
            user.setEmail(userRequest.email);
            if(userValidationService.isUserValid(user))
                userRepository.save(user);
        }
        return null;
    }

    public void delete(int userId) {
        Optional<User> userFromDb = Optional.ofNullable(userRepository.findById(userId));
        if (!userFromDb.isEmpty()){
            User user = userFromDb.get();
            userRepository.delete(user);
        }
    }



}
