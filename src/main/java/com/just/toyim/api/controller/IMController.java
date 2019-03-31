package com.just.toyim.api.controller;


import com.just.toyim.service.UserService;
import com.just.toyim.service.meta.HttpResponse;
import com.just.toyim.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RestController
public class IMController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/chatroom")
    public ModelAndView toChatroom(HttpSession session) {
        Object userId = session.getAttribute(Constants.USER_TOKEN);

        if(userId == null){
            return new ModelAndView("login.html");
        }
        return new ModelAndView("/public/chatroom.html");

    }

    @RequestMapping(value = "/chatroom/get_userinfo", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponse getUserInfo(HttpSession session) {
        Object userId = session.getAttribute(Constants.USER_TOKEN);
        return userService.getUserById((String) userId);
    }


}
