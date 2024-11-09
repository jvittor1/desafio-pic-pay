package com.example.desafio_pic_pay.controllers;

import com.example.desafio_pic_pay.dtos.UserDTO;
import com.example.desafio_pic_pay.entities.User;
import com.example.desafio_pic_pay.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDTO userDto){
        var user = userService.createUser(userDto);
        return ResponseEntity.created(URI.create(("/users/" + user.getId()))).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception{
        var user = userService.getUserBytId(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping()
    public  ResponseEntity<List<User>> listUsers(){
        var users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO userDto) throws Exception{
        var user = userService.updateUser(id, userDto);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
