package com.records.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.records")
@Slf4j
public class RecordsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordsAdminApplication.class, args);
        log.info("----- Records Admin Start Successfully -----");
    }

}
