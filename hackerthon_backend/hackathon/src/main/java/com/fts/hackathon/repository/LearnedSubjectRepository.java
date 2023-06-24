package com.fts.hackathon.repository;

import com.fts.hackathon.model.LearnedSubject;
import com.fts.hackathon.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LearnedSubjectRepository extends JpaRepository<LearnedSubject, Integer> {

    Optional<List<LearnedSubject>> findAllByStudent(Student student);

    Optional<List<LearnedSubject>> findAllByStudentAndLearnedSubjectSemester(Student student, String learnedSubjectSemester);



}
