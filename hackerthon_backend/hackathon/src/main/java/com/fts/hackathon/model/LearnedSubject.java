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
public class LearnedSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int learnedSubjectNum;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student", nullable = false)
    private Student student;

    @Column
    private String learnedSubjectSemester;

    @Column(nullable = false, length = 500)
    private String learnedSubjectName;

    @Column
    private String learnedSubjectGrade;

}
