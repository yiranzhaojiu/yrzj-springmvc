package com.yiranzhaojiu.book.controller;

import com.yiranzhaojiu.springmvc.springframework.build.annotation.RestController;
import com.yiranzhaojiu.springmvc.springframework.build.annotation.RequestMapping;

@RestController
public class HomeController {

    @RequestMapping(value = "/say/hello",method = "GET")
    public String sayHello(){
        return "Test Say Hello";
    }

    @RequestMapping(value = "/say/hello1",method = "POST")
    public String sayHelloOne(){
        return "Test Say Hello post";
    }
}
