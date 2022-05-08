package com.esgi.framework_JEE.application.validation;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class EmailValidationTest {

    EmailValidation emailValidation= new EmailValidation();

    @Test
    public void should_be_valid(){
        var email = "lucas.jehanno@gmail.com";
        assertThat(emailValidation.isValid(email)).isEqualTo(true);
    }

    @Test
    public void should_be_invalid(){
        var email = "lucas.jehanno@";
        assertThat(emailValidation.isValid(email)).isEqualTo(false);
    }
}