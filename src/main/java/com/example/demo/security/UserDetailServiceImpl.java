package com.example.demo.security;

import com.example.demo.dto.UserAccountDto;
import com.example.demo.model.Role;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("first")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        UserAccountDto user = null;
        boolean enabled = true;
        user = adminService.findUserByUsername(username);

        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(user.getRole()));
        if (user.getStatus().equals("INACTIVE")) {
            enabled = false;
        }
        userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), enabled, true, true, true, roles);
        return userDetails;
    }
}
