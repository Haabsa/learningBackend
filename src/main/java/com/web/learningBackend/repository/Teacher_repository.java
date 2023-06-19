package com.web.learningBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.learningBackend.model.Teacher;

@Repository

public interface Teacher_repository extends JpaRepository<Teacher,Integer> {
    
}
