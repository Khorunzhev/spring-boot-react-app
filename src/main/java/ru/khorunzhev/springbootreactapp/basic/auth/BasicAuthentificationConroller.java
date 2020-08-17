package ru.khorunzhev.springbootreactapp.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BasicAuthentificationConroller {

    @GetMapping(path = "/basicauth")
    public AuthentificationBean helloWorldBean() {
        return new AuthentificationBean("You are authentificated");
    }

}
