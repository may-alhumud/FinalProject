package com.example.evaluation.controller;

import com.example.evaluation.DTO.API;
import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.EvaluationItem;
import com.example.evaluation.service.EvaluationItemService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/evaluationitem")
@RequiredArgsConstructor
public class EvaluationItemController {
    private final EvaluationItemService evaluationItemService;
    Logger logger= LoggerFactory.getLogger(EvaluationItemController.class);


    @GetMapping("/evaluation-item")
    public ResponseEntity<List<EvaluationItem>> getEvaluationItem(){
        logger.info("get all EvaluationItem");
        List<EvaluationItem> evaluationItem=evaluationItemService.getEvaluationItem();
        return ResponseEntity.status(HttpStatus.OK).body(evaluationItem);
    }

    @PostMapping("/add-evaluation-item")
    public ResponseEntity<API> addEvaluationItem(@RequestBody @Valid EvaluationItem evaluationItem){
        logger.info("add EvaluationItem");
        evaluationItemService.addEvaluationItem(evaluationItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("New EvaluationItem added !",201));
    }


    @PutMapping("/update-evaluation-item/{id}")
    public ResponseEntity<API> UpdateEvaluationItem(@RequestBody @Valid EvaluationItem evaluationItem,@PathVariable @Valid Integer id) throws RecordNotFoundException {
        logger.info("update  EvaluationItem");
        evaluationItemService.updateEvaluationItem(evaluationItem,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("EvaluationItem Update",201));
    }


    @DeleteMapping("/remove-EvaluationItem/{id}")
    public ResponseEntity<API> removeEvaluationItem(@PathVariable @Valid Integer id){
         logger.info(" remove  EvaluationItem");
        evaluationItemService.removeEvaluationItem(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("Delete EvaluationItem",201));
    }


}
