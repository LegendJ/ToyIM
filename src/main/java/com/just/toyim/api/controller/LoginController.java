package com.just.toyim.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.MessageFormat;


@Controller
@RestController
public class LoginController {

    @RequestMapping(value = {"login", "/"}, method = RequestMethod.GET)
    public ModelAndView toLogin() {
        return new ModelAndView("/public/index.html");
    }


}
