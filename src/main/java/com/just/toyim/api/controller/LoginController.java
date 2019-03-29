package com.just.toyim.api.controller;

import com.just.toyim.pojo.HttpResponse;
import com.just.toyim.pojo.User;
import com.just.toyim.service.UserService;
import com.just.toyim.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.MessageFormat;


@Controller
@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;


    @RequestMapping(value = {"login", "/"}, method = RequestMethod.GET)
    public ModelAndView toLogin() {
        return new ModelAndView("/public/index.html");
    }


    @PostMapping("/login")
    @ResponseBody
    public HttpResponse login(HttpSession session, User user) {

        HttpResponse response = userService.login(user);
        if (response.getStatus() == 200) {
            session.setAttribute(Constants.USER_TOKEN, user.getId());
        }
        return response;
    }

    @PostMapping("/logout")
    @ResponseBody
    public HttpResponse logout(HttpSession session) {
        Object userId = session.getAttribute(Constants.USER_TOKEN);
        if (userId == null) {
            return new HttpResponse().error("请先登陆");
        }
        session.removeAttribute(Constants.USER_TOKEN);
        LOGGER.info(MessageFormat.format("userId为 {0} 的用户已注销登录!", userId));
        return new HttpResponse().success();
    }
}

}
