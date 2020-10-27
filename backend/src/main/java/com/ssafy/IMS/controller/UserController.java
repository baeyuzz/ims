package com.ssafy.IMS.controller;

import com.ssafy.IMS.exception.ApiException;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.SignUpRequest;
import com.ssafy.IMS.payload.UserIdentityAvailability;
import com.ssafy.IMS.payload.UserProfile;
import com.ssafy.IMS.repository.UserRepository;
import com.ssafy.IMS.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Api(value = "로그인, 회원가입, 유저에 관한 정보 처리")
@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "이메일이 사용가능한지 확인")
    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(@RequestParam(value = "email") String email) {
        UserIdentityAvailability userIdentityAvailability = userService.checkEmailAvailability(email);
        return new ResponseEntity< >(userIdentityAvailability, HttpStatus.OK);
    }

    @ApiOperation(value = "유저 정보 보기")
    @GetMapping("/{username}/profile")
    public ResponseEntity<UserProfile> getUSerProfile(@PathVariable(value = "username") String username) {
        UserProfile userProfile = userService.getUserProfile(username);

        return new ResponseEntity< >(userProfile, HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.");
        }

        String username = signUpRequest.getName().toLowerCase();

        String email = signUpRequest.getEmail().toLowerCase();

        String password = signUpRequest.getPassword();

        User user = new User(email, password, username);

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/{userId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "회원가입에 성공했습니다."));
    }
}
