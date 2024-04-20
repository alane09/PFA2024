package com.elearning.enstab.exam.service;

import com.elearning.enstab.exam.repository.Exam;
import com.elearning.enstab.exam.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;


    @Autowired
    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }


    public void saveExam(Exam exam) {
        examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }
    public void deleteExamById(int id) {
        // Delete exam by ID
        examRepository.deleteById((long) id);
    }
    public List<Exam> searchExams(String query) {
        return examRepository.findByNiveauContainingOrSemestreContainingOrSpecialiteContainingOrMatiereContaining(query, query, query, query);
    }


}