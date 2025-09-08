package com.pedroacbg.blog.controllers;

import com.pedroacbg.blog.controllers.docs.AuthControllerDocs;
import com.pedroacbg.blog.domain.dto.AuthResponse;
import com.pedroacbg.blog.domain.dto.LoginRequest;
import com.pedroacbg.blog.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth/login")
@Tag(name = "Auth", description = "Endpoint de controle de Login")
public class AuthController implements AuthControllerDocs {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        UserDetails userDetails = authenticationService.authenticate(request.getEmail(), request.getPassword());
        String tokenValue = authenticationService.generateToken(userDetails);

        AuthResponse response = new AuthResponse();
        response.setToken(tokenValue);
        response.setExpiresIn(84600);

        return ResponseEntity.ok(response);
    }

}
