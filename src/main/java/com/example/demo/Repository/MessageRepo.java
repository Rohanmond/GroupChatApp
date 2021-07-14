package com.example.demo.Repository;

import com.example.demo.Entity.Messages;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Messages,Integer> {

}
