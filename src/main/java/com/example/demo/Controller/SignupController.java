package com.example.demo.Controller;

import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    @Autowired
    private UserService userService;
    @GetMapping()
    public String signupView(){
        return "signup";
    }
    @PostMapping()
    public String signupUser(@ModelAttribute("user") User user, Model model){
        String signupError=null;
        if(!userService.isUsernameAvailable(user.getUsername())){
            signupError="Username already exists.";
        }
        if(signupError==null){

            String ack=userService.addNewUser(user);
            if(ack==null || !ack.equals("saved")){
                signupError="There was an error during signIn";
            }
        }
        if(signupError==null){
            model.addAttribute("signupSuccess",true);
        }else{
            model.addAttribute("signupError",signupError);
        }
        return "signup";
    }
}
