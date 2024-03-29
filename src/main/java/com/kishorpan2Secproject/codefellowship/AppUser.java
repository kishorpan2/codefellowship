package com.kishorpan2Secproject.codefellowship;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity

public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique =true)
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String dateofbirth;
    private String bio;

    @OneToMany(mappedBy="creator")
    private List<Post> postList;


    public AppUser(){};

    public AppUser(String username, String password, String firstname, String lastname, String dateofbirth, String bio){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.bio = bio;
    }
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "followers_following",
            joinColumns = {@JoinColumn(name ="follower_id")},
            inverseJoinColumns = {@JoinColumn(name = "following_id")}

    )
    private Set<AppUser> followers = new HashSet<>();
    @ManyToMany(mappedBy = "followers")
    private Set<AppUser> following = new HashSet<>();

    public String getFirstname() {
        return this.firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    public String getDateofbirth() {
        return this.dateofbirth;
    }
    public String getBio() {
        return this.bio;
    }
    public long getId() {
        return this.id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public Set<AppUser> getFollowers() {
        return followers;
    }

    public void setFollowers(AppUser followers) {
        this.followers.add(followers);
    }

    public Set<AppUser> getFollowing() {
        return following;
    }

    public void setFollowing(AppUser following) {
        this.following.add(following);

    }
}
