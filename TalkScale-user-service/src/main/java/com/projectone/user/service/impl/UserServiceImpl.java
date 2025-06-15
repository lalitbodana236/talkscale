package com.projectone.user.service.impl;

import com.projectone.common.dto.UserDto;
import com.projectone.common.util.GenericMapper;
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
    public List<UserDto> findAll() {
       
        

       List<AppUser> appUserList = userRepository.findAll();
        List<UserDto> userList = (List<UserDto>) GenericMapper.mapToDto(appUserList, UserDto.class);
        return userList;
    }
    //Handles profile update and retrieval
}
