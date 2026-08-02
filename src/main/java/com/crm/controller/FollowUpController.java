package com.crm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.entity.FollowUp;
import com.crm.service.FollowUpService;

@RestController
@RequestMapping("/api/followups")
public class FollowUpController {

    private final FollowUpService followUpService;

    public FollowUpController(FollowUpService followUpService) {
        this.followUpService = followUpService;
    }

    @PostMapping
    public FollowUp create(@RequestBody FollowUp followUp) {
        return followUpService.createFollowUp(followUp);
    }

    @GetMapping
    public List<FollowUp> getAll() {
        return followUpService.getAllFollowUps();
    }

    @GetMapping("/{id}")
    public FollowUp getById(@PathVariable Long id) {
        return followUpService.getFollowUpById(id);
    }

    @PutMapping("/{id}")
    public FollowUp update(@PathVariable Long id,
                           @RequestBody FollowUp followUp) {
        return followUpService.updateFollowUp(id, followUp);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        followUpService.deleteFollowUp(id);
    }
}