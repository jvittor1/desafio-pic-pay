package com.example.desafio_pic_pay.services;

import com.example.desafio_pic_pay.dtos.UserDTO;
import com.example.desafio_pic_pay.entities.User;
import com.example.desafio_pic_pay.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User(userDTO.fullName(), userDTO.cpf(), userDTO.email(), userDTO.password(), userDTO.balance(), userDTO.userType());
        userRepository.save(user);
        return user;
    }

    public User getUserBytId(Long id) throws RuntimeException {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        user.update(userDTO);
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepository.delete(user);
    }


}
