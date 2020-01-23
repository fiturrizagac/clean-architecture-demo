package com.befitca.cleanarch.adapter.web;

import com.befitca.cleanarch.usecase.port.in.LoginCredentials;
import com.befitca.cleanarch.usecase.port.in.LoginUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentications")
public class LoginRestController {

    @Autowired
    private LoginUseCase loginUseCase;

    @PostMapping
    public ResponseEntity login(final RequestEntity<LoginCredentials> loginCredentials) {

        loginUseCase.login(loginCredentials.getBody());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public String hello() {
        return "Greetings from Adapters!";
    }

}
