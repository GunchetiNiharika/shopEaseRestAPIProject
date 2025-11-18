package com.project.eCommerce.Service;

import com.project.eCommerce.entity.User;
import com.project.eCommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        user.setName(updatedUser.getName());
        user.setPassword(updatedUser.getPassword());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
