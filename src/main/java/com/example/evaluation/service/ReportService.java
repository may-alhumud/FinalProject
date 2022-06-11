package com.example.evaluation.service;

import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Report;
import com.example.evaluation.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
public class ReportService {
    private  final ReportRepository reportRepository;

    public List<Report> getReport() {

        return reportRepository.findAll();
    }

    public void addReport(Report comment) {

        reportRepository.save(comment);
    }

    public boolean updateReport(Report comment,Integer id) throws RecordNotFoundException {
        Optional<Report> currentReport=reportRepository.findById(id);
        if (!currentReport.isEmpty()){
            throw new RecordNotFoundException("record not found for id "+id);
        }
        var update = currentReport.get();
        update.setNote(comment.getNote());
        reportRepository.save(update);
        return true;
    }


    public boolean removeReport(Integer id){
        Optional<Report> currentUser=reportRepository.findById(id);
        if (!currentUser.isEmpty()){
            return false;
        }
        reportRepository.deleteById(id);
        return true;
    }

}
