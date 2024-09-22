package com.met.focusflow.service;

import java.util.Optional;

import com.met.focusflow.models.Feedback;

public interface FeedBackService {
    Feedback createFeedback(Feedback feedback);
    Optional<Feedback>getById(Long id);
}
