package com.amdtia.usermanagement.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {

    @RequestMapping("index")
    public String getIndex(Model model){
        return "index";
    }



}
