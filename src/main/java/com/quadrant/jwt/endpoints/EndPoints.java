package com.quadrant.jwt.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endpoints")
public class EndPoints {

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public String ordinaryUsersOnly(){
        return "Ordinary users only";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminUsersOnly(){
        return "Admin users only";
    }

}
