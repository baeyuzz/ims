package com.ssafy.IMS.service;

import com.ssafy.IMS.model.User;
import com.ssafy.IMS.payload.ApiResponse;
import com.ssafy.IMS.payload.SignUpRequest;
import com.ssafy.IMS.payload.UserIdentityAvailability;
import com.ssafy.IMS.payload.UserProfile;
import com.sun.security.auth.UserPrincipal;

public interface UserService {

    UserIdentityAvailability checkEmailAvailability(String email);

    UserProfile getUserProfile(String name);

    User addUser(SignUpRequest signUpRequest);

    User updateUser(User newUser, String name, UserPrincipal currentUser);

    ApiResponse deleteUser(UserPrincipal currentUser);
}
