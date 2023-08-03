package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.model.project;

public interface projectRepository extends JpaRepository<project,Long> {
    
}
