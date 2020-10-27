package com.ssafy.IMS.controller;

import com.ssafy.IMS.payload.UserIdentityAvailability;
import com.ssafy.IMS.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "로그인, 회원가입, 유저에 관한 정보 처리")
@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "이메일이 사용가능한지 확인")
    @GetMapping("/checkEmailAvailability")
    public ResponseEntity<UserIdentityAvailability> checkEmailAvailability(@RequestParam(value = "email") String email) {
        UserIdentityAvailability userIdentityAvailability = userService.checkEmailAvailability(email);
        return new ResponseEntity< >(userIdentityAvailability, HttpStatus.OK);
    }

}
