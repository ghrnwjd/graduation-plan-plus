package com.fts.hackathon.controller;

import com.fts.hackathon.dto.KeywordDto;
import com.fts.hackathon.dto.ResponseDto;
import com.fts.hackathon.dto.UserInputDto;
import com.fts.hackathon.model.LearnedSubject;
import com.fts.hackathon.model.Student;
import com.fts.hackathon.model.Subject;
import com.fts.hackathon.repository.LearnedSubjectRepository;
import com.fts.hackathon.service.StudentService;
import com.fts.hackathon.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
public class ApiController {

    private final StudentService studentService;
    private final SubjectService subjectService;

    public ApiController(StudentService studentService, LearnedSubjectRepository learnedSubjectRepository, SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping("/get")
    public List<String> get() {
        return Arrays.asList("안녕", "이거받아");
    }

    @GetMapping("/get/dict")
    public HashMap<String, String> dictget() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("xx", "xx");
        hashMap.put("yy", "yy");

        return hashMap;
    }

    @PostMapping("/student/login")
    public ResponseDto login(@RequestBody Student student) {
        return studentService.login(student.getStudentNum());
    }


    @PostMapping("/student/learned/{studentNum}")
    public ResponseDto learnedSubject(@RequestBody LearnedSubject learnedSubject, @PathVariable String studentNum) {
        return studentService.learnedSubject(learnedSubject, studentNum);

    }

    @GetMapping("/student/creditInfo/{studentNum}")
    public HashMap<String, Integer> getRemainedCredit(@PathVariable String studentNum) {
        return studentService.calculateCreditInfo(studentNum);
    }

    @GetMapping("/student/remainedMajor/{studentNum}")
    public HashMap<String, String> getRemainedMajor(@PathVariable String studentNum) {
        return studentService.getRemainEssentialSubject(studentNum);
    }

    @PostMapping("/student/add/{department}")
    public ResponseDto addStudent(@RequestBody Student student, @PathVariable String department) {
        return studentService.addStudent(student, department);
    }

    @PostMapping("/student/recommend/{studentNum}")
    public HashMap<String, String> KeywordRecommend(@RequestBody KeywordDto keywordDto, @PathVariable String studentNum) {
        return subjectService.recommendSubject6(keywordDto, studentNum);
    }

    @PostMapping("/student/recommend/input/{studentNum}")
    public HashMap<String, String> userInputRecommend(@RequestBody UserInputDto userInputDto, @PathVariable String studentNum) {
        return subjectService.getKeywordsFromUserInput(studentNum, userInputDto);
    }

    @GetMapping("/student/info/{studentNum}")
    public Student getStudentInfo(@PathVariable String studentNum) {
        return studentService.findStudent(studentNum);
    }

    @GetMapping("/student/learnedSubject/semesterGrade/{studentNum}")
    public HashMap<String, Double> getSemesterGrade(@PathVariable String studentNum){
        return subjectService.calculateSemesterGrade(studentNum);
    }

    @PostMapping("/student/recommend/allSubjectInfo/{studentNum}")
    public HashMap<String, String[]> recommendAllSubjectInfo(@RequestBody KeywordDto keywordDto, @PathVariable String studentNum) {
        return subjectService.recommedAllSubjectInfo(keywordDto, studentNum);
    }

    @PostMapping("/student/recommend/subjectInfo")
    public HashMap<String, String> recommendsubjectInfo(@RequestBody UserInputDto userInputDto) throws Exception {
        return subjectService.subjectInfo(userInputDto);
    }

}
