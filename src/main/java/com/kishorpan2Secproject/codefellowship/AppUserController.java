package com.kishorpan2Secproject.codefellowship;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AppUserController {

    @Autowired
    AppUserRepo appUserRepo;

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/users")

    public RedirectView createUser(String username, String password){
        AppUser newUser = new AppUser(username, bCryptPasswordEncoder.encode(password));
        appUserRepo.save(newUser);
        return new RedirectView("/");
    }

}
