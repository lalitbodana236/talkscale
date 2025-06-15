package com.projectone.user.service;

import com.projectone.common.dto.UserDto;

import java.util.List;

public interface IUserService {
    //User profile actions
    
    List<UserDto> findAll();
}
