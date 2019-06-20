package com.kishorpan2Secproject.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import com.kishorpan2Secproject.codefellowship.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;




import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.xml.crypto.Data;
import java.security.Principal;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Controller
public class PostController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    AppUserRepo appUserRepo;

    @GetMapping("/create/post")
    public String createPost(Principal p, Model m){
        String name = p.getName();

        AppUser currentUser = (AppUser) appUserRepo.findByUsername(name);
        m.addAttribute("currentUser", currentUser);

        return "createPost";
    }
    @PostMapping(value ="/create/post")
    public RedirectView createPost(@RequestParam String body, Model m ,Principal p){
        AppUser user = appUserRepo.findByUsername(p.getName());// creating a user who is logged in
        Date date = new Date();
        long time = date.getTime();
        Post newPost = new Post(body, time, user);
        postRepository.save(newPost);

        return new RedirectView("/");
    }


}
