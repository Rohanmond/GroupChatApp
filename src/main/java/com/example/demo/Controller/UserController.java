package com.example.demo.Controller;

import com.example.demo.Entity.Users;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/showAllUsers")
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping("/addAUser")
    public String addAUser(@RequestBody User user){
        return userService.addNewUser(user);
    }
}
