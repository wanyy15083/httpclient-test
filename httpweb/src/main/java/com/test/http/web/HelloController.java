package com.test.http.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping
public class HelloController {

    @ResponseBody
    @RequestMapping("hello")
    public String hello(String name) {
        return name;
    }
}
