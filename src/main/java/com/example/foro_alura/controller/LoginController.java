package com.example.foro_alura.controller;


import com.example.foro_alura.domain.dto.auth.DTOAuth;
import com.example.foro_alura.domain.dto.auth.DTOToken;
import com.example.foro_alura.serivce.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private  LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<DTOToken> login(
            @RequestBody
            @Valid
            DTOAuth dtoAuth
    ){
        String token = loginService.login(dtoAuth);
        return ResponseEntity.ok(new DTOToken(token));
    }
}
