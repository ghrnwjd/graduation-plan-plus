package com.fts.hackathon.service;

import com.fts.hackathon.dto.ResponseDto;
import com.fts.hackathon.model.Department;
import com.fts.hackathon.model.LearnedSubject;
import com.fts.hackathon.model.ResponseStatus;
import com.fts.hackathon.model.Student;
import com.fts.hackathon.repository.LearnedSubjectRepository;
import com.fts.hackathon.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LearnedSubjectRepository learnedSubjectRepository;

    @Autowired
    private SubjectService subjectService;

    public ResponseDto login(String studentNum) {
        Student student =  studentRepository.findByStudentNum(studentNum).orElseGet(()-> {
            return null;
        });

        if(student != null) {
            return ResponseDto.builder()
                    .responseStatus(ResponseStatus.OK)
                    .build();
        }

        return ResponseDto.builder()
                .responseStatus(ResponseStatus.NO)
                .build();
    }

    public Student findStudent(String studentNum) {
        return studentRepository.findByStudentNum(studentNum).orElseGet(()-> {
            return null;
        });
    }

    public ResponseDto learnedSubject(LearnedSubject learnedSubject, String studentNum) {
        Student student = findStudent(studentNum);
        if(student == null) {
            return ResponseDto.builder()
                    .responseStatus(ResponseStatus.NO)
                    .build();
        }
        learnedSubject.setStudent(student);
        learnedSubjectRepository.save(learnedSubject);

//        throw.Exception();

        return ResponseDto.builder()
                .responseStatus(ResponseStatus.OK)
                .build();
    }
    public HashMap<String, Integer> calculateCreditInfo(String studentNum)  {

        Student student = findStudent(studentNum);

        HashMap<String, Integer> needsCredit = needsCreditInfo(student.getStudentDepartmentType());

        List<LearnedSubject> learnedSubjectList = learnedSubjectRepository.findAllByStudent(student).orElseGet(()-> {
            return null;
        });

        List<Integer> studentCreditList = subjectService.getSubjectCreditInfo(learnedSubjectList, student.getStudentDepartment());

        int studentMajorCredit = studentCreditList.get(0), studentMinorCredit = studentCreditList.get(1);

//        throw new Exception();

        if(learnedSubjectList == null) {
            return null;
//            return ResponseDto.builder()
//                    .responseStatus(ResponseStatus.NO)
//                    .build();
        }


//        System.out.println("전공필요학점: " + needsCredit.get("전공"));
//        System.out.println("전공남은학점: " + (needsCredit.get("전공") - studentMajorCredit ));
//        System.out.println("교양필요학점: " + needsCredit.get("교양"));
//        System.out.println("교양남은학점: " + (needsCredit.get("교양") - studentMinorCredit ));

        HashMap<String, Integer> postCreditList = new HashMap<String, Integer>();
        postCreditList.put("전공필요학점", needsCredit.get("전공"));
        postCreditList.put("전공들은학점", studentMajorCredit);
        postCreditList.put("전공남은학점", (needsCredit.get("전공") - studentMajorCredit ));
        postCreditList.put("교양필요학점", needsCredit.get("교양"));
        postCreditList.put("교양들은학점", studentMinorCredit);
        postCreditList.put("교양남은학점", (needsCredit.get("교양") - studentMinorCredit ));

        return postCreditList;

//        return ResponseDto.builder()
//                .responseStatus(ResponseStatus.OK)
//                .build();

    }

    public HashMap<String, Integer> needsCreditInfo(int departmentType) {

        HashMap<String, Integer> creditInfo = new HashMap<>();

        if(departmentType == 0) {
            creditInfo.put("전공", 57);
            creditInfo.put("이중전공", 42);
            creditInfo.put("부전공", 0);
        }
        else if (departmentType == 1) {
            creditInfo.put("전공", 57);
            creditInfo.put("이중전공", 0);
            creditInfo.put("부전공", 21);
        }
        else if (departmentType == 2) {
            creditInfo.put("전공", 75);
            creditInfo.put("이중전공", 0);
            creditInfo.put("부전공", 0);
        }
        else if (departmentType == 3) {
            creditInfo.put("전공", 75);
            creditInfo.put("이중전공", 0);
            creditInfo.put("부전공", 21);
        }
        creditInfo.put("교양", 32);

        return creditInfo;
    }

    public ResponseDto addStudent(Student student, String department) {
        if(department.equals("정보통신공학과")) {
            student.setStudentDepartment(Department.정보통신공학과);
        }
        else if(department.equals("컴퓨터공학부")) {
            student.setStudentDepartment(Department.컴퓨터공학부);
        }
        else {
            return ResponseDto.builder()
                    .responseStatus(ResponseStatus.NO)
                    .build();
        }

        studentRepository.save(student);

        return ResponseDto.builder()
                .responseStatus(ResponseStatus.OK)
                .build();
    }


    public HashMap<String, String> getRemainEssentialSubject(String studentNum) {

        Student student = findStudent(studentNum);

        HashMap<String, Integer> needsCredit = needsCreditInfo(student.getStudentDepartmentType());

        List<LearnedSubject> learnedSubjectList = learnedSubjectRepository.findAllByStudent(student).orElseGet(()-> {
            return null;
        });

        List<String> studentRemainedMajorList = subjectService.getRemainEssentialSubject(learnedSubjectList, student);

        if(studentRemainedMajorList == null) {
//            System.out.println("null 입니다.");
//            return ResponseDto.builder()
//                    .responseStatus(ResponseStatus.NO)
//                    .build();
            return null;
        }

        HashMap<String, String> remainMajorSubjectWithIndex = new HashMap<String,String>();

        for (int i = 0; i < 3; i++){
            if(studentRemainedMajorList.size() < i){
                break;
            }
            remainMajorSubjectWithIndex.put("남은전공" + (i+1) ,studentRemainedMajorList.get(i));
        }

        return remainMajorSubjectWithIndex;

//        System.out.println("남은 전공과목은 다음과 같습니다.");
//        for(String temp : studentRemainedMajorList) {
//            System.out.println(temp + " ");
//        }
//
//        return ResponseDto.builder()
//                .responseStatus(ResponseStatus.OK)
//                .build();

    }


}
