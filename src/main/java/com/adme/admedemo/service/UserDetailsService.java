package com.adme.admedemo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
    // UserDetails = User (구현체)
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
