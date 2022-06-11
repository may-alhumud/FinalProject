package com.example.evaluation.controller;

import com.example.evaluation.DTO.API;
import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Website;
import com.example.evaluation.service.WebsiteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/website")
@RequiredArgsConstructor
public class WebsiteController {
    private final WebsiteService websiteService;
    Logger logger= LoggerFactory.getLogger(WebsiteController.class);



    @GetMapping
    public ResponseEntity<List<Website>> getWebsite(){
        logger.info("gt all website");
        List<Website> website=websiteService.getWebsites();
        return ResponseEntity.status(HttpStatus.OK).body(website);
    }

    @PostMapping("/add-website")
    public ResponseEntity<API> addWebsite(@RequestBody @Valid Website website){
        logger.info("add website");
        websiteService.addWebsite(website);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("Record added !",201));
    }


    @PutMapping("/update-website/{id}")
    public ResponseEntity<API> UpdateWebsite(@RequestBody @Valid Website website,@PathVariable @Valid Integer id) throws RecordNotFoundException {
         logger.info("update website");
        websiteService.updateWebsite(website,id);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("Record updated",201));
    }


    @DeleteMapping("/remove-website/{id}")
    public ResponseEntity<API> removeWebsite(@PathVariable @Valid Integer id){
         logger.info("remove website");
       var removed = websiteService.removeWebsite(id);
       if(removed) {
           return ResponseEntity.status(HttpStatus.OK).body(
                   new API("Record deleted", 200));
       }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new API("Record is not deleted", 400));
    }


}
