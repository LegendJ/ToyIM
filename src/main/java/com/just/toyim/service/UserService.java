package com.just.toyim.service;


import com.just.toyim.pojo.HttpResponse;
import com.just.toyim.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    HttpResponse login(User user);
}
