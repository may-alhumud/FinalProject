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
public class EvaluationItemService {
    private final EvaluationItemRepository evaluationItemRepository;
    private final UserRepository userRepository;


    public List<EvaluationItem> getEvaluationItem() {

        return evaluationItemRepository.findAll();
    }

    public void addEvaluationItem(EvaluationItem evaluationItem) {

        evaluationItemRepository.save(evaluationItem);
    }

    public boolean updateEvaluationItem(EvaluationItem evaluationItem,Integer id) throws RecordNotFoundException {
        Optional<EvaluationItem> currentEvaluationItem=evaluationItemRepository.findById(id);
        if (!currentEvaluationItem.isEmpty()){
            throw new RecordNotFoundException("record not found for id "+id);
        }
        var update = currentEvaluationItem.get();
        update.setName(evaluationItem.getName());
        evaluationItemRepository.save(update);
        return true;
    }


    public boolean removeEvaluationItem(Integer id){
        Optional<EvaluationItem> currentUser=evaluationItemRepository.findById(id);
        if (!currentUser.isEmpty()){
            return false;
        }
        evaluationItemRepository.deleteById(id);
        return true;
    }




}
