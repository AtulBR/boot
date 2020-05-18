package com.cts.Service;

import com.cts.Exception.ResourceNotFoundException;
import com.cts.Model.User;
import com.cts.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Optional<User> userDb = this.userRepository.findById(user.getId());
        if (userDb.isPresent()) {
            User updateUser = userDb.get();
            updateUser.setId(user.getId());
            updateUser.setName(user.getName());
            userRepository.save(updateUser);
            return updateUser;
        } else {
            throw new ResourceNotFoundException("Record not found with id " + user.getId());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserById(int id) {
        Optional<User> userDb = this.userRepository.findById(id);
        if(userDb.isPresent()) {
            return userDb.get();
        } else {
            throw new ResourceNotFoundException("Record not found with id " + id);
        }
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> userDb = this.userRepository.findById(id);
        if(userDb.isPresent()) {
            this.userRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Record not found with id " + id);
        }
    }
}