package com.utils.tools.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils")
public class IndexController {

    @GetMapping("/login")
    public void login(){

    }
}
