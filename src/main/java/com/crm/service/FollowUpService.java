package com.crm.service;

import java.util.List;

import com.crm.entity.FollowUp;

public interface FollowUpService {

    FollowUp createFollowUp(FollowUp followUp);

    List<FollowUp> getAllFollowUps();

    FollowUp getFollowUpById(Long id);

    FollowUp updateFollowUp(Long id, FollowUp followUp);

    void deleteFollowUp(Long id);

}