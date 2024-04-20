package com.elearning.enstab.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {


    List<Exam> findByNiveauContainingOrSemestreContainingOrSpecialiteContainingOrMatiereContaining(String niveau , String semestre,String specialite, String matiere);
}