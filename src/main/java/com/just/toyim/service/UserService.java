package com.just.toyim.service;


import com.just.toyim.service.meta.HttpResponse;
import com.just.toyim.service.meta.UserDto;


public interface UserService {

    HttpResponse login(UserDto user);

    HttpResponse getUserById(String userId);
}
