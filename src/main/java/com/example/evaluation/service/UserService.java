package com.example.evaluation.service;

import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.EvaluationItem;
import com.example.evaluation.model.User;
import com.example.evaluation.repository.EvaluationItemRepository;
import com.example.evaluation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EvaluationItemRepository evaluationItemRepository;

    public List<User> getUser() {
        return userRepository.findAll();
    }

    public void adduser(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(User user,Integer id) throws RecordNotFoundException {
        Optional<User> currentUser=userRepository.findById(id);
        if (currentUser.isEmpty()){
            throw new RecordNotFoundException("record not found for id "+id);
        }
        var update = currentUser.get();
        update.setUsername(user.getUsername());
        update.setPassword(user.getPassword());
        update.setRole(user.getRole());
        userRepository.save(update);
        return true;
    }


    public boolean removeUser(Integer id){
        Optional<User> currentUser=userRepository.findById(id);
        if (currentUser.isEmpty()){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }


}
