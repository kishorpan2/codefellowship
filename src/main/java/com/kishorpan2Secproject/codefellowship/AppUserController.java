package com.kishorpan2Secproject.codefellowship;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/users")

    public RedirectView createUser(String username, String password,String firstName, String lastName,String dateOfBirth,String bio ){
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password),firstName, lastName, dateOfBirth,bio);
        appUserRepo.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new RedirectView("/");
    }
    @GetMapping("/login")
    public String getLogInPage(){
        return "login";
    }

    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }


    @PostMapping("/signup")
    public RedirectView signup(String username, String password, String firstName, String lastName,String dateOfBirth,String bio ){
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password),firstName, lastName, dateOfBirth,bio);
        appUserRepo.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/");
    }

}
