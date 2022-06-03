package com.esgi.framework_JEE;

import java.util.UUID;

public class TestFixtures {

    public static String randomEmail() {
        return "random-" + UUID.randomUUID().toString() + "@example.com";
    }
}
