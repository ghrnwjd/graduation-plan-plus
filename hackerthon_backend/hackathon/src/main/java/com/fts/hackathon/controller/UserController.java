package com.fts.hackathon.controller;

import com.fts.hackathon.model.Student;
import com.fts.hackathon.repository.StudentRepository;
import com.fts.hackathon.service.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin("*")
@Controller
public class UserController {

    private final SubjectService subjectService;

    public UserController(StudentRepository studentRepository, SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }


    @GetMapping("/readSubjectCSV")
    public String readSubjectCSV() {
        subjectService.readSubjectCSV();
        return "index.html";
    }

    @GetMapping("/readKeywordCSV")
    public String readKeywordCSV() {
        subjectService.readKeywordsCSV();
        return "index.html";
    }

}
