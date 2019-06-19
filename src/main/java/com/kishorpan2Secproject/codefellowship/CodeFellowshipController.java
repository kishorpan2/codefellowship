package com.kishorpan2Secproject.codefellowship;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CodeFellowshipController{


    @GetMapping("/")

    public String getCodeFellowship(Principal p, Model m){

        AppUser currentUser = (AppUser) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        m.addAttribute("principal", currentUser);


        return "codefellowship";
    }

//    @GetMapping




}