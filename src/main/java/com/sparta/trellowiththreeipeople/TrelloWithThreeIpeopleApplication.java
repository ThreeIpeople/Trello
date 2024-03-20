package com.sparta.trellowiththreeipeople;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class TrelloWithThreeIpeopleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrelloWithThreeIpeopleApplication.class, args);
    }

}
