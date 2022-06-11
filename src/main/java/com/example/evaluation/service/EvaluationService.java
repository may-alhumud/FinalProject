package com.example.evaluation.service;

import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Evaluation;
import com.example.evaluation.model.EvaluationItem;
import com.example.evaluation.model.User;
import com.example.evaluation.repository.EvaluationItemRepository;
import com.example.evaluation.repository.EvaluationRepository;
import com.example.evaluation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UserRepository userRepository;
    private final EvaluationItemRepository evaluationItemRepository;

    public List<Evaluation> getEvaluation() {
        return evaluationRepository.findAll();
    }

    public void addEvaluation(Evaluation evaluation) {

        evaluationRepository.save(evaluation);
    }

    public boolean updateEvaluation(Evaluation evaluation,Integer id) throws RecordNotFoundException {
        Optional<Evaluation> currentEvaluation=evaluationRepository.findById(id);

        if (currentEvaluation.isEmpty()){
            throw new RecordNotFoundException("record not found for id "+id);
        }
        var update = currentEvaluation.get();
        update.setItem(evaluation.getItem());
        update.setVote(evaluation.getVote());
        evaluationRepository.save(update);
        return true;
    }


    public boolean removeEvaluation(Integer id){
        Optional<Evaluation> found =evaluationRepository.findById(id);
        if (found.isEmpty()){
            return false;
        }
        evaluationRepository.deleteById(id);
        return true;
    }




}
