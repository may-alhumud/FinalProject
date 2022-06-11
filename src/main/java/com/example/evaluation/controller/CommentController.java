package com.example.evaluation.controller;

import com.example.evaluation.DTO.API;
import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Comment;
import com.example.evaluation.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    Logger logger= LoggerFactory.getLogger(CommentController.class);


    @GetMapping
    public ResponseEntity<List<Comment>> getComment(){
        logger.info("get all Comment");
        List<Comment> evaluation=commentService.getComment();
        return ResponseEntity.status(HttpStatus.OK).body(evaluation);
    }

    @PostMapping("/add-evaluation")
    public ResponseEntity<API> addComment(@RequestBody @Valid Comment evaluation){
        logger.info("add Comment");
        commentService.addComment(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("New Comment added !",201));
    }


    @PutMapping("/update-evaluation/{id}")
    public ResponseEntity<API> UpdateComment(@RequestBody @Valid Comment evaluation,@PathVariable @Valid Integer id) throws RecordNotFoundException {
        logger.info("update Comment");
        commentService.updateComment(evaluation,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new API("Comment Update",200));
    }


    @DeleteMapping("/remove-evaluation/{id}")
    public ResponseEntity<API> removeComment(@PathVariable @Valid Integer id){
        logger.info(" remove Comment");
        var removed   = commentService.removeComment(id);
        if(removed) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new API("Record deleted", 200));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new API("Record is not deleted", 400));
    }


}
