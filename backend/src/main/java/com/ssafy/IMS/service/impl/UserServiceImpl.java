package com.ssafy.IMS.service.impl;

import com.ssafy.IMS.exception.BadRequestException;
import com.ssafy.IMS.exception.ResourceNotFoundException;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.UserInfoRequest;
import com.ssafy.IMS.payload.UserIdentityAvailability;
import com.ssafy.IMS.payload.UserProfile;
import com.ssafy.IMS.repository.UserRepository;
import com.ssafy.IMS.service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserIdentityAvailability checkEmailAvailability(String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @Override
    public UserProfile getUserProfile(String email) {
        User user =userRepository.getUserByEmail(email);
        return new UserProfile(user.getId(),user.getName(),user.getEmail(),user.getCompany1(),user.getCompany2(),user.getCompany3());
    }

    @Override
    public User addUser(UserInfoRequest userInfoRequest) {
        if (userRepository.existsByEmail(userInfoRequest.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "이미 존재하는 유저입니다.");
            throw new BadRequestException(apiResponse);
        }
        String username = userInfoRequest.getName().toLowerCase();
        String email = userInfoRequest.getEmail().toLowerCase();
        String password = userInfoRequest.getPassword();
        String company1 = userInfoRequest.getCompany1();
        String company2 = userInfoRequest.getCompany2();
        String company3 = userInfoRequest.getCompany3();
        User user = new User(email, password, username,company1,company2,company3);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserInfoRequest userInfoRequest) {
        Optional<User> oldUser = userRepository.findByEmail(userInfoRequest.getEmail());
        oldUser.ifPresent(newUser ->{
            newUser.setName(userInfoRequest.getName());
            newUser.setPassword(userInfoRequest.getPassword());
            newUser.setCompany1(userInfoRequest.getCompany1());
            newUser.setCompany2(userInfoRequest.getCompany2());
            newUser.setCompany3(userInfoRequest.getCompany3());
            userRepository.save(newUser);
        });
        return userRepository.findByEmail(userInfoRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException(String.format("유저를 찾지 못헀습니다")));
    }

    @Override
    public ApiResponse deleteUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", email));
        System.out.println(user.getId());
        userRepository.deleteById(user.getId());
        return new ApiResponse(Boolean.TRUE,  email + "님이 회원탈퇴하셨습니다.");
    }


}
