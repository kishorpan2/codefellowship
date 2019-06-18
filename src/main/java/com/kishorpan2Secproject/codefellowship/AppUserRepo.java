package com.kishorpan2Secproject.codefellowship;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AppUserRepo extends CrudRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
