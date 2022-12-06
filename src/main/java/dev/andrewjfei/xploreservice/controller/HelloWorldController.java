package dev.andrewjfei.xploreservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/hello-world")
public class HelloWorldController {

    @GetMapping
    public ResponseEntity<String> helloWorld() {
        String response = "Hello World!";
        return new ResponseEntity<>(response, OK);
    }
}
