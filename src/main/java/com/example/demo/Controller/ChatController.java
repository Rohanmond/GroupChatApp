package com.example.demo.Controller;

import com.example.demo.Model.ChatForm;
import com.example.demo.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private MessageService messageService;
    @GetMapping
    public String getChatPage(@ModelAttribute("chatForm") ChatForm chatForm, Model model){
        model.addAttribute("chatMessages",this.messageService.getAllMessages());
        return "chat";
    }
    @PostMapping
    public String postChatMessages(Authentication authentication,ChatForm chatForm,Model model){
        chatForm.setUsername(authentication.getName());
        this.messageService.addMessage(chatForm);
        chatForm.setMessage("");
        model.addAttribute("chatMessages",this.messageService.getAllMessages());
        return "chat";
    }
}
