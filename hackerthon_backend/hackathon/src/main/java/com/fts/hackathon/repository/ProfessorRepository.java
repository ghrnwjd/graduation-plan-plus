package com.fts.hackathon.repository;

import com.fts.hackathon.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Optional<Professor> findByProfessorName(String professorName);
}
