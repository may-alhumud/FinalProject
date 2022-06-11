package com.example.evaluation.service;

import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.SocialAccount;
import com.example.evaluation.repository.SocialAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SocialAccountService {
    private final SocialAccountRepository socialAccountRepository;

    public List<SocialAccount> getAccounts() {
        return socialAccountRepository.findAll();
    }

    public void addAccount(SocialAccount account) {
        socialAccountRepository.save(account);
    }

    public boolean updateAccount(SocialAccount account, Integer id) throws RecordNotFoundException {
        Optional<SocialAccount> found = socialAccountRepository.findById(id);
        if (found.isEmpty()) {
            throw new RecordNotFoundException("record not found for id " + id);
        }
        var update = found.get();
        update.setName(account.getName());
        update.setUrl(account.getUrl());
        update.setPlatform(account.getPlatform());

        socialAccountRepository.save(update);
        return true;
    }


    public boolean removeAccount(Integer id) {

        Optional<SocialAccount> found = socialAccountRepository.findById(id);

        if (found.isEmpty()) {
            return false;
        }
        socialAccountRepository.deleteById(id);
        return true;
    }
}