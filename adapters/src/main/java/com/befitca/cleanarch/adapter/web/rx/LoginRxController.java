package com.befitca.cleanarch.adapter.web.rx;

import com.befitca.cleanarch.usecase.port.in.LoginCredentials;
import com.befitca.cleanarch.usecase.port.in.LoginUseCase;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/authentications/v2")
public class LoginRxController {

    private LoginUseCase loginUseCase;

    public LoginRxController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping
    public Mono login(final RequestEntity<LoginCredentials> loginCredentials) {
        loginUseCase.login(loginCredentials.getBody());
        return Mono.just(ResponseEntity.ok().build());
    }

}
