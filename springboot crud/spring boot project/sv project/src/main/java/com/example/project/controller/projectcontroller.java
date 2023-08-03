package com.example.project.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.model.project;
import com.example.project.repository.projectRepository;


@RestController
@RequestMapping("/api")
public class projectcontroller {
    
    @Autowired
    projectRepository projectRepository;

    //to show all datas in the table
    @GetMapping("/show_all")
    public List<project> getAllpProjects(){
        return (List<project>) projectRepository.findAll();    
    }
      @PostMapping("/create")
    public ResponseEntity<project> createproject(@RequestBody project project){
        project _project = projectRepository
            .save(new project(project.getFirstname(), project.getLastname(), project.getPassword()));
        return new ResponseEntity<>(_project, HttpStatus.CREATED);
    }

     @DeleteMapping("/delete_all")
    public ResponseEntity<HttpStatus> deleteAllproject(){
        projectRepository.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/insert/{id}")
    public ResponseEntity<project> updateproject(@PathVariable("id") long id, @RequestBody project project){
            Optional<project> __project = projectRepository.findById(id);
        if(__project.isPresent()){
            project _project = __project.get();
            _project.setFirstname(project.getFirstname());
            _project.setLastname(project.getLastname());
            _project.setPassword(project.getPassword());

            return new ResponseEntity<>(projectRepository.save(_project), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
    }
}
