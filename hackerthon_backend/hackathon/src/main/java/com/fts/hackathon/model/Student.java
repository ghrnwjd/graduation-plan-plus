package com.fts.hackathon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

    @Id
    private String studentNum;

    @Column(nullable = false, length = 1000)
    private String studentName;

    @Column
    private Department studentDepartment;
    // 추가해야 될 것
    // 0: 이중전공, 1:부전공, 2: 전공심화, 3: 전공심화+부전공
    @Column
    private int studentDepartmentType;

    @Column
    private int keywordPoint1;
    @Column
    private int keywordPoint2;
    @Column
    private int keywordPoint3;
    @Column
    private int keywordPoint4;
    @Column
    private int keywordPoint5;
    @Column
    private int keywordPoint6;
    @Column
    private int keywordPoint7;


    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<LearnedSubject> learnedSubjectList;
}
