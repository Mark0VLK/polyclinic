package com.noirix.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomValuesGenerator {

    public String generateRandomString() {
        return RandomStringUtils.random(10, true, true);
    }

    public String uuidGenerator() {
        return UUID.randomUUID().toString();
    }

}