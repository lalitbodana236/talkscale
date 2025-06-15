package com.projectone.user.service;

import com.projectone.user.entity.AppUser;

import java.util.List;

public interface IUserService {
    //User profile actions
    
    List<AppUser> findAll();
}
