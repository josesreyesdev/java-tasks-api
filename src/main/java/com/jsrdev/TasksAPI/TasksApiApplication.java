package com.jsrdev.TasksAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource("file:${user.dir}/.env")
public class TasksApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasksApiApplication.class, args);
    }

}
