package com.techbridge.apis.controller;

import com.techbridge.apis.model.dto.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController extends BaseController {

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }


    @PostMapping("/register")
    public ResponseEntity<Object> login2(@RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

    @PostMapping("/password/reset")
    public ResponseEntity<Object> resetPassword(@RequestBody LoginDto loginDto) {
        return userService.setPassword(loginDto);
    }


    @PostMapping("/password/change")
    public ResponseEntity<Object> changePassword(@RequestBody LoginDto loginDto) {
        return userService.changePassword(loginDto);
    }
}
