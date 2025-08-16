package com.demo.catalogue.administration.controller;

import com.example.api.StudentsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StudentController implements StudentsApi {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/test-logs")
    public String testLogs() {
        logger.trace("This is a TRACE log");
        logger.debug("This is a DEBUG log");
        logger.info("This is an INFO log");
        logger.warn("This is a WARN log");
        logger.error("This is an ERROR log");
        return "Logs generated! Check console and logs/application.log";
    }

}
