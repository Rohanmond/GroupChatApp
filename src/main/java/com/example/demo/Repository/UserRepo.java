package com.example.demo.Repository;

import com.example.demo.Entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<Users,Integer> {
    Users findUsersByUsername(String username);
}
