package nl.edemtb.mtbclinicsapplication.services;


import nl.edemtb.mtbclinicsapplication.dtos.RegisteredUserDto;

import nl.edemtb.mtbclinicsapplication.models.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final RegisteredUserService registeredUserService;

    public CustomUserDetailsService(RegisteredUserService registeredUserService) {
        this.registeredUserService = registeredUserService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        RegisteredUserDto registereduserDto = registeredUserService.getUser(username);


        String password = registereduserDto.getPassword();

        Set<Authority> authorities = registereduserDto.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authority authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }

}