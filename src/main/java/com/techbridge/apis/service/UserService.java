package com.techbridge.apis.service;

import com.techbridge.apis.excepions.CustomErrorException;
import com.techbridge.apis.excepions.CustomExtraErrorException;
import com.techbridge.apis.model.User;
import com.techbridge.apis.model.dto.LoginDto;
import com.techbridge.apis.model.dto.UserDto;
import com.techbridge.apis.repository.UserRepository;
import com.techbridge.apis.util.PasswordEncoder;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    public ResponseEntity<Object> getUsers() {
        List<UserDto> UserDtos =  userRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(dto -> new UserDto(dto.getUsrId(),dto.getMemId(),dto.getUsername(),dto.getEmail(),""))
                .toList();

        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved",UserDtos);
    }

    public ResponseEntity<Object> getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        UserDto userdto = user.map(dto -> new UserDto(dto.getUsrId(),dto.getMemId(),dto.getUsername(),dto.getEmail(),"")).orElseThrow(null);
        throw new CustomExtraErrorException(HttpStatus.OK, "Records successfully retrieved", userdto);
    }

    public ResponseEntity<Object> createNewUser(User user) {
        String hashMsg  = PasswordEncoder.get_SHA_512_SecurePassword(user.getPassword());
        Optional<User> optionalUser = userRepository.findByUsernameorEmail(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new CustomErrorException(HttpStatus.FOUND, "User already exists");
        }
        if (null != user.getUsername() && null != user.getPassword()) {
            user.setPassword(hashMsg);
            userRepository.save(user);
            throw new CustomErrorException(HttpStatus.CREATED, "User created successfully");
        } else {
            throw new CustomErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "Something has gone wrong");
        }
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "No such user with id: " + id);
        }
        userRepository.deleteById(id);
        throw new CustomErrorException(HttpStatus.OK, "User deleted Successfully");
    }

    @Transactional
    public ResponseEntity<Object> updateUser(Long UserId, UserDto dto) {
        User user = userRepository.findById(UserId).orElseThrow(() ->
                new IllegalArgumentException("No such User with id: " + UserId));
        if (!Objects.isNull(dto)) {
            user.setPassword(dto.getPassword());
            userRepository.save(user);
            throw new CustomErrorException(HttpStatus.CREATED, "User updated successfully");
        }

        throw new CustomErrorException(HttpStatus.CREATED, "No update done");
    }
    public ResponseEntity<Object> changePassword(@RequestBody LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsernameorEmail(loginDto.getUsername());
        if (user.isEmpty()) {
            throw new CustomErrorException(HttpStatus.UNAUTHORIZED, "User does not exist");
        }else if (user.get().getPassword().equals(passwordEncoder.get_SHA_512_SecurePassword(loginDto.getPassword()))) {
            throw new CustomErrorException(HttpStatus.BAD_REQUEST, "Old Password and New Password are the same");
        } else {
            userRepository.updateForgotPassword(passwordEncoder.get_SHA_512_SecurePassword(loginDto.getPassword()), loginDto.getUsername());
            throw new CustomErrorException(HttpStatus.OK, "Password successfully reset");
        }
    }

    public ResponseEntity<Object> setPassword(@RequestBody LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsernameorEmail(loginDto.getUsername());
        if (user.isEmpty()) {
            throw new CustomErrorException(HttpStatus.UNAUTHORIZED, "User with username " + loginDto.getUsername() + " not found!");
        }
        String token = passwordEncoder.get_SHA_512_SecurePassword(new Random().nextInt(999999) + "_" + System.currentTimeMillis());
        userRepository.updateRegistrationToken(token, user.get().getUsername());
        JSONObject jsonObj = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("reset_token", token);
        jsonObj.put("data", data);
        throw new CustomExtraErrorException(HttpStatus.OK, "Password set successfully", jsonObj);

    }

    public ResponseEntity<Object> loginUser(@RequestBody LoginDto loginDto) {
        Optional<User> user = userRepository.findByUsernameorEmail(loginDto.getUsername());
        if (user.isEmpty()) {
            throw new CustomErrorException(HttpStatus.NOT_FOUND, "User does not exist");
        }
        Optional<User> userObj = userRepository.findByUsernameAndPassword(loginDto.getUsername(), passwordEncoder.get_SHA_512_SecurePassword(loginDto.getPassword()));
        if (userObj.isEmpty()) {
            throw new CustomErrorException(HttpStatus.UNAUTHORIZED, "Invalid login credentials");
        } else if (userObj.get().getToken()!=null) {
            throw new CustomErrorException(HttpStatus.IM_USED, "Please reset password");
        } else {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("refreshToken","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");
            jsonObj.put("expiresIn","3600");
            jsonObj.put("firstname",userObj.get().getUsername());
            jsonObj.put("email",userObj.get().getEmail());
            throw new CustomExtraErrorException(HttpStatus.OK, "Login Successful",jsonObj);
        }
    }
}


