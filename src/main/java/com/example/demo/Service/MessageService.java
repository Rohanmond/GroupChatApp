package com.example.demo.Service;

import com.example.demo.Entity.Messages;
import com.example.demo.Model.ChatForm;
import com.example.demo.Repository.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepo messageRepo;

    public List<Messages> getAllMessages(){
        return (List<Messages>) messageRepo.findAll();
    }

    public void addMessage(ChatForm chatForm){
        Messages m=new Messages(null,chatForm.getUsername(),chatForm.getMessage());
        messageRepo.save(m);
    }


}
