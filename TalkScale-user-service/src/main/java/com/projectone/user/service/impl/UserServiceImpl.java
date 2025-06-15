package com.projectone.user.service.impl;

import com.projectone.user.entity.AppUser;
import com.projectone.user.repository.IUserRepository;
import com.projectone.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    
    private final IUserRepository userRepository;
    
    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }
    //Handles profile update and retrieval
}
