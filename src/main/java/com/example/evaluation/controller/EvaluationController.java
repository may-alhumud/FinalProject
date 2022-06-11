package com.example.evaluation.controller;

import com.example.evaluation.DTO.API;
import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Evaluation;
import com.example.evaluation.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/evaluation")
@RequiredArgsConstructor
public class EvaluationController {
    private final EvaluationService evaluationService;
    Logger logger= LoggerFactory.getLogger(EvaluationController.class);


    @GetMapping
    public ResponseEntity<List<Evaluation>> getEvaluation(){
        logger.info("get all Evaluation");
        List<Evaluation> evaluation=evaluationService.getEvaluation();
        return ResponseEntity.status(HttpStatus.OK).body(evaluation);
    }

    @PostMapping("/add-evaluation")
    public ResponseEntity<API> addEvaluation(@RequestBody @Valid Evaluation evaluation){
        logger.info("add Evaluation");
        evaluationService.addEvaluation(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("New Evaluation added !",201));
    }


    @PutMapping("/update-evaluation/{id}")
    public ResponseEntity<API> UpdateEvaluation(@RequestBody @Valid Evaluation evaluation,@PathVariable @Valid Integer id) throws RecordNotFoundException {
         logger.info("update Evaluation");
        evaluationService.updateEvaluation(evaluation,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new API("Evaluation Update",200));
    }


    @DeleteMapping("/remove-evaluation/{id}")
    public ResponseEntity<API> removeEvaluation(@PathVariable @Valid Integer id){
        logger.info(" remove Evaluation");
        var removed   = evaluationService.removeEvaluation(id);
        if(removed) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new API("Record deleted", 200));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new API("Record is not deleted", 400));
    }



}
