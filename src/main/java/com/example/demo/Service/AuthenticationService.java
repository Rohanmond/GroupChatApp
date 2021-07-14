package com.example.demo.Service;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class AuthenticationService implements AuthenticationProvider {
    @Autowired
    private UserService userService;
    @Autowired
    private HashService hashService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getName();
        String password=authentication.getCredentials().toString();
        Users users=userService.getUserByName(username);
        if(users!=null){
            String encodedSalt=users.getSalt();
            String hashedPassword=hashService.getHashedValue(password,encodedSalt);
            if(users.getPassword().equals(hashedPassword)){
                return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
