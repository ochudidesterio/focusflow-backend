package com.met.focusflow.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.met.focusflow.models.Feedback;
import com.met.focusflow.repository.FeedBackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl implements FeedBackService{

    private final FeedBackRepository feedBackRepository;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedBackRepository.save(feedback);
    }

    @Override
    public Optional<Feedback> getById(Long id) {
        return feedBackRepository.findById(id);
    }
    
}
