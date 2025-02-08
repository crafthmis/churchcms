package com.techbridge.apis.controller;

import com.techbridge.apis.model.User;
import com.techbridge.apis.model.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController extends BaseController {


    @GetMapping("/users")
    public ResponseEntity<Object> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/user/add")
    public ResponseEntity<Object> addNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Long id, @RequestParam(required = false) UserDto dto) {
        return userService.updateUser(id, dto);
    }
}
