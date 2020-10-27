package com.ssafy.IMS.controller;

import com.ssafy.IMS.exception.ApiException;
import com.ssafy.IMS.exception.BadRequestException;
import com.ssafy.IMS.exception.ResourceNotFoundException;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.*;
import com.ssafy.IMS.repository.UserRepository;
import com.ssafy.IMS.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Api(value = "로그인, 회원가입, 유저에 관한 정보 처리")
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable(value = "username") String username) {
        UserProfile userProfile = userService.getUserProfile(username);

        return new ResponseEntity< >(userProfile, HttpStatus.OK);
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserInfoRequest userInfoRequest) {
        User result = userService.addUser(userInfoRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/{userId}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(Boolean.TRUE, "회원가입에 성공했습니다."));
    }

    @ApiOperation(value = "유저 정보 업데이트")
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserInfoRequest userInfoRequest) {
        User updatedUSer = userService.updateUser(userInfoRequest);

        return new ResponseEntity< >(updatedUSer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "회원탈퇴")
    @DeleteMapping("/{email}")
    public ResponseEntity<ApiResponse> deleteUser(@RequestParam(value = "email") String email) {
        ApiResponse apiResponse = userService.deleteUser(email);

        return new ResponseEntity< >(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest loginRequest){
        User curUser = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", loginRequest.getEmail()));
        if(!curUser.getPassword().equals(loginRequest.getPassword())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다");
        }
        return curUser;
    }
}
