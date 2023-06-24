package com.fts.hackathon.repository;

import com.fts.hackathon.model.LearnedSubject;
import com.fts.hackathon.model.Professor;
import com.fts.hackathon.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    Optional<List<Subject>> findAllBySubjectNameAndSubjectSemester(String learnedSubjectName, String learnedSubjectSemester);

    Optional<List<Subject>> findAllBySubjectNameNotIn(List<String> subjectName);

    List<Subject> findAllByProfessor(Professor professor);

    Optional<Subject> findBySubjectSemesterAndSubjectDepartmentAndSubjectName(String subjectSemester, String subjectDepartment, String subjectName);

    Optional<Subject> findByProfessorAndSubjectNameAndSubjectSemester(Professor professor, String subjectName, String subjectSemester);
}
