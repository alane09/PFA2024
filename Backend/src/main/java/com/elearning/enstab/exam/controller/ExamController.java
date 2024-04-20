package com.elearning.enstab.exam.controller;

import com.elearning.enstab.exam.repository.Exam;
import com.elearning.enstab.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/exams")
@CrossOrigin(origins = "http://localhost:8080")
public class ExamController {

    private final ExamService examService;
    @Autowired
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ResponseEntity<String> uploadExam(@RequestParam("exam") MultipartFile file,
                                             @RequestParam("niveau")  String niveau,
                                             @RequestParam("semestre")  String semestre,
                                             @RequestParam("specialite")  String specialite,
                                             @RequestParam("matiere")  String matiere,
                                             @RequestParam("examen")  Integer examen,
                                             @RequestParam("cours")  Integer cours) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Select a file to upload");
        }
        UUID identifier = UUID.randomUUID();
        String path = System.getProperty("user.dir") + "/src/main/resources/exams/" + identifier + ".pdf";
        file.transferTo(new File(path));

        examService.saveExam(new Exam(file.getOriginalFilename(), identifier, niveau, semestre, specialite, matiere, examen, cours));

        return ResponseEntity.ok("File uploaded successfully");
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<Resource> getPdf(@PathVariable String identifier) throws IOException {
        String path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "exams", identifier + ".pdf").toString();
        File file = new File(path);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=" + identifier + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
    @GetMapping
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable("id") int id) {
        // Call ExamService to delete exam by ID
        examService.deleteExamById(id);
        return ResponseEntity.ok("Exam deleted successfully");
    }
    @GetMapping("/search")
    public ResponseEntity<List<Exam>> searchExams(@RequestParam("query") String query) {
        List<Exam> results = examService.searchExams(query);
        return ResponseEntity.ok(results);
    }

}