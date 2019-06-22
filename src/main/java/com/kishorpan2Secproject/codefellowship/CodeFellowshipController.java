package com.kishorpan2Secproject.codefellowship;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;


import java.security.Principal;
import java.util.List;


@Controller
public class CodeFellowshipController{

    @Autowired
    AppUserRepo appUserRepo;


    @GetMapping("/")
    public String getCodeFellowship(Principal p, Model m){

        AppUser currentUser = (AppUser) appUserRepo.findByUsername(p.getName());
        m.addAttribute("principal", currentUser);

        return "codefellowship";
    }
    @GetMapping("/user/discover")
    public String discoverUsers(Principal p, Model m){
//        AppUser currentUser = (AppUser)appUserRepo.findByUsername(p.getName());
//        m.addAttribute("currentUser",currentUser);
        List<AppUser> allUsers = (List)appUserRepo.findAll();
        allUsers.remove(appUserRepo.findByUsername(p.getName()));
        allUsers.removeAll(appUserRepo.findByUsername(p.getName()).getFollowing());
        m.addAttribute("allUsers",allUsers);
        return "discover";
    }

    @PostMapping(value = "/user/addFriends")
    public RedirectView addFriends(@RequestParam String selectedUser, Model m, Principal p){
        long userId = Long.parseLong(selectedUser);
        AppUser thatUser = appUserRepo.findById(userId).get();
        AppUser currentUser = appUserRepo.findByUsername(p.getName());

        currentUser.setFollowing(thatUser);
        thatUser.setFollowers(currentUser);

        appUserRepo.save(currentUser);
        appUserRepo.save(currentUser);

        m.addAttribute("currentUser",currentUser);
        List<AppUser> allUsers = (List)appUserRepo.findAll();
        allUsers.removeAll(currentUser.getFollowing());
        m.addAttribute("allUsers",allUsers);
        return new RedirectView("/user/discover");

    }
    @GetMapping("/feed")
    public String getFeeds( Model m, Principal p){
        AppUser currentUser = appUserRepo.findByUsername(p.getName());
//        List<AppUser> followingList = (List)currentUser.getFollowing();
        m.addAttribute("currentUser",currentUser);
        return "feed";


    }



}