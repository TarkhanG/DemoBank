package com.tarkhan.app.service.impl;

import com.tarkhan.app.entity.User;
import com.tarkhan.app.mapper.UserProfileMapper;
import com.tarkhan.app.model.authentication.UserProfileResponseModel;
import com.tarkhan.app.respoitory.UserRepository;
import com.tarkhan.app.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponseModel getUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User " + email + " Not Found"));

        return userProfileMapper.toUserProfile(user);
    }
}
