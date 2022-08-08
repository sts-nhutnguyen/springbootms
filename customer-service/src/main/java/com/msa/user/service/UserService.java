package com.msa.user.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msa.user.model.Department;
import com.msa.user.model.User;
import com.msa.user.model.UserDTO;

@Service
public class UserService{

    @Autowired
    private RestTemplate restTemplate;

    private static List<User> users = new ArrayList<>();

    public User create(User user){
        user.setId(users.size() + 1);
        users.add(user);

        // Call notification service to send notification
        String message = restTemplate.getForObject("http://localhost:9003/notification/"+user.getId()+"/?target-type=user", String.class);

        System.out.println(message);

        return user;

    }

    public List<User> get(){
        return users;
    }

    public UserDTO getById(int id){
        User user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(()->new IllegalStateException("User not found"));

        Department department = restTemplate.getForObject("http://localhost:9001/departments/"+user.getDepartmentId(), Department.class);

        return new UserDTO(user, department);
    }
}
