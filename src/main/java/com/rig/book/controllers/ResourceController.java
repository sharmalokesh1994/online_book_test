package com.rig.book.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping({"/customer/hello"})
    public String helloUser(){
        return "Hello User";
    }

    @RequestMapping({"/admin/hello"})
    public String helloAdmin(){
        return "Hello Admin";
    }

}
