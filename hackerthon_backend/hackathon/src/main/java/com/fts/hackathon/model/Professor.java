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
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int professorNum;

    @Column(nullable = false, length = 1000)
    private String professorName;

    @Column
    private Keyword keyword1;

    @Column
    private Keyword keyword2;

    @Column
    private Keyword keyword3;

    @Column
    private Keyword keyword4;

    @Column
    private Keyword keyword5;

    @Column
    private Keyword keyword6;

    @Column
    private Keyword keyword7;

    @JsonIgnore
    @OneToMany(mappedBy = "professor")
    private List<Subject> subjectList;

}
