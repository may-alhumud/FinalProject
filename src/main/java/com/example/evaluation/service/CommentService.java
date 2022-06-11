package com.example.evaluation.service;

import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Comment;
import com.example.evaluation.repository.CommentRepository;
import com.example.evaluation.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class CommentService {
    private  final CommentRepository commentRepository;

    public List<Comment> getComment() {

        return commentRepository.findAll();
    }

    public void addComment(Comment comment) {

        commentRepository.save(comment);
    }

    public boolean updateComment(Comment comment,Integer id) throws RecordNotFoundException {
        Optional<Comment> currentComment=commentRepository.findById(id);
        if (!currentComment.isEmpty()){
            throw new RecordNotFoundException("record not found for id "+id);
        }
        var update = currentComment.get();
        update.setComment(comment.getComment());
        commentRepository.save(update);
        return true;
    }


    public boolean removeComment(Integer id){
        Optional<Comment> currentUser=commentRepository.findById(id);
        if (!currentUser.isEmpty()){
            return false;
        }
        commentRepository.deleteById(id);
        return true;
    }

}
