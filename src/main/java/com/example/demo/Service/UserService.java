package com.example.demo.Service;

import com.example.demo.Entity.Users;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private HashService hashService;

    public List<Users> getAllUsers() {
        List<Users> all = (List<Users>) userRepo.findAll();
        return all;
    }
    public String addNewUser(User user){
        SecureRandom random=new SecureRandom();
        byte[] salt=new byte[16];
        random.nextBytes(salt);
        String encodedSalt= Base64.getEncoder().encodeToString(salt);
        String hashedPassword=hashService.getHashedValue(user.getPassword(),encodedSalt);
        userRepo.save(new Users(null,user.getUsername(),encodedSalt,hashedPassword));
        return "saved";
    }
    public Users getUserByName(String username){
        return userRepo.findUsersByUsername(username);
    }
    public boolean isUsernameAvailable(String username){
        return userRepo.findUsersByUsername(username)==null;
    }
}
