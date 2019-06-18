package com.kishorpan2Secproject.codefellowship;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CodeFellowshipController{


    @GetMapping("/codefellowship")
    public String getCodeFellowship(){

        return "codefellowship";
    }



}