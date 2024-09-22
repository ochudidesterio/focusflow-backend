package com.met.focusflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.met.focusflow.models.Feedback;

public interface FeedBackRepository extends JpaRepository<Feedback,Long> {
    
}
