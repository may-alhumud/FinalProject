package com.example.evaluation.controller;


import com.example.evaluation.DTO.API;
import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.SocialAccount;
import com.example.evaluation.service.SocialAccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class SocialAccountController {
    private final SocialAccountService accountService;
    Logger logger= LoggerFactory.getLogger(SocialAccountController.class);


    @GetMapping
    public ResponseEntity<List<SocialAccount>> getSocialAccount(){
        logger.info("get all SocialAccount");
        List<SocialAccount> account= accountService.getAccounts();
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping("/add-account")
    public ResponseEntity<API> addSocialAccount(@RequestBody @Valid SocialAccount account){
         logger.info("add SocialAccount");
        accountService.addAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new API("Record added !",201));
    }


    @PutMapping("/update-account/{id}")
    public ResponseEntity<API> UpdateSocialAccount(@RequestBody @Valid SocialAccount account,@PathVariable @Valid Integer id) throws RecordNotFoundException {
        logger.info("update SocialAccount");
        accountService.updateAccount(account,id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new API("Record updated",200));
    }


    @DeleteMapping("/remove-account/{id}")
    public ResponseEntity<API> removeSocialAccount(@PathVariable @Valid Integer id){
        logger.info("update SocialAccount");
        var removed = accountService.removeAccount(id);
        if(removed) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new API("Record deleted", 200));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new API("Record is not deleted", 400));
    }


}
