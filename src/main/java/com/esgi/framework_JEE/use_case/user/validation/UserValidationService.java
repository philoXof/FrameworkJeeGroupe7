package com.esgi.framework_JEE.use_case.user.validation;

import com.esgi.framework_JEE.application.validation.EmailValidation;
import com.esgi.framework_JEE.use_case.user.Domain.entities.User;

public class UserValidationService {


    public UserValidationService(){
    }

    public boolean isUserValid(User user) {

        return  !user.getFirstname().isBlank() && !user.getLastname().isBlank() && new EmailValidation().isValid(user.getEmail()) && user.getPassword().length() >= 8 && user.getPassword().length() <= 30;
        //todo : voir pour renvoyer la bonne erreur
    }

}
