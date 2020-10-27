package com.ssafy.IMS.service.impl;

import com.ssafy.IMS.exception.BadRequestException;
import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.SignUpRequest;
import com.ssafy.IMS.payload.UserIdentityAvailability;
import com.ssafy.IMS.payload.UserProfile;
import com.ssafy.IMS.repository.UserRepository;
import com.ssafy.IMS.service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

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
    public UserProfile getUserProfile(String name) {
        User user =userRepository.getUserByName(name);
        return new UserProfile(user.getId(),user.getName(),user.getEmail());
    }

    @Override
    public User addUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "이미 존재하는 유저입니다.");
            throw new BadRequestException(apiResponse);
        }
        String username = signUpRequest.getName().toLowerCase();

        String email = signUpRequest.getEmail().toLowerCase();

        String password = signUpRequest.getPassword();

        User user = new User(email, password, username);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User newUser, String name, UserPrincipal currentUser) {
        return null;
    }

    @Override
    public ApiResponse deleteUser(UserPrincipal currentUser) {
        return null;
    }


}
