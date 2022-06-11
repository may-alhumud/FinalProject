package com.example.evaluation.service;

import com.example.evaluation.exception.RecordNotFoundException;
import com.example.evaluation.model.Website;
import com.example.evaluation.repository.WebsiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WebsiteService {
    private final WebsiteRepository websiteRepository;

    public List<Website> getWebsites() {
        return websiteRepository.findAll();
    }

    public void addWebsite(Website site) {
        websiteRepository.save(site);
    }

    public boolean updateWebsite(Website site,Integer id) throws RecordNotFoundException {
        Optional<Website> found = websiteRepository.findById(id);
        if (found.isEmpty()){
            throw new RecordNotFoundException("record not found for id "+id);
        }
        var update = found.get();
        update.setName(site.getName());
        update.setUrl(site.getUrl());
        update.setShortDescription(site.getShortDescription());

        websiteRepository.save(update);
        return true;
    }


    public boolean removeWebsite(Integer id){

        Optional<Website> found = websiteRepository.findById(id);

        if (found.isEmpty()){
            return false;
        }
        websiteRepository.deleteById(id);
        return true;
    }


}
