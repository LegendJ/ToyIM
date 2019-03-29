package com.just.toyim.service;


import com.just.toyim.service.meta.HttpResponse;
import com.just.toyim.service.meta.UserDto;
import org.springframework.stereotype.Service;


public interface UserService {

    HttpResponse login(UserDto user);
}
