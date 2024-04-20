package com.elearning.enstab.exam.service;

import com.elearning.enstab.exam.repository.Exam;

import java.util.List;

public interface ExamServices {
    List<Exam> getAllExams();
    void saveExam(Exam exam);
    void deleteExamById(int id);
    Exam getExamById(int id);
    List<Exam> searchExams(String query);
}
