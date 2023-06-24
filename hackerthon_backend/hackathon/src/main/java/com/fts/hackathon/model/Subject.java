package com.fts.hackathon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subjectNum;

    // 과목명
    @Column(nullable = false)
    private String subjectName;

    // 개설학과
    @Column
    private String subjectDepartment;

    // 전공, 교양
    @Column(nullable = false)
    private SubjectType subjectType;

    // 수업 학점
    @Column(nullable = false)
    private int subjectCredit;


    @Column(nullable = false)
    private String subjectSemester;

    // 수업 시간
    @Column
    private String subjectTime;

    // 수업 필수 여부
    @Column
    private Essential subjectEssential;
    // 교수
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "professor", nullable = true) // 나중엔 바꾸기
    private Professor professor;
}
