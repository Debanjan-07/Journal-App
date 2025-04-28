package com.example.journal.service;


import com.example.journal.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;



    @ParameterizedTest
    @ValueSource(strings = {
            "ram",
            "rohit",
            "deba"
    })

    @Test
    public void findByUserName(String name){
//        User user = userRepository.findByUserName("deba");
        assertNotNull(userRepository.findByUserName(name),"failed for" + name);
//        assertNotNull(userRepository.findByUserName("ram"));
    }


    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    @Disabled
    @ParameterizedTest
    public void test(int a,int b,int expected){
        assertEquals(expected,a + b);
    }

}
