package com.example.evaluation.controller;

import com.example.evaluation.DTO.API;
import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Report;
import com.example.evaluation.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    Logger logger= LoggerFactory.getLogger(ReportController.class);

    @GetMapping
    public ResponseEntity<List<Report>> getReport(){
        logger.info("get all Report");
        List<Report> product=reportService.getReport();
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping("/add-product")
    public ResponseEntity<API> addReport(@RequestBody @Valid Report product){
        logger.info("add Report");
        reportService.addReport(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("Record added !",201));
    }


    @PutMapping("/update-product/{id}")
    public ResponseEntity<API> UpdateReport(@RequestBody @Valid Report product,@PathVariable @Valid Integer id) throws RecordNotFoundException {
        logger.info("update Report");
        reportService.updateReport(product,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new API("Record updated",200));
    }


    @DeleteMapping("/remove-product/{id}")
    public ResponseEntity<API> removeReport(@PathVariable @Valid Integer id){
        logger.info("add book");
        var removed = reportService.removeReport(id);
        if(removed) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new API("Record deleted", 200));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new API("Record is not deleted", 400));
    }


}
