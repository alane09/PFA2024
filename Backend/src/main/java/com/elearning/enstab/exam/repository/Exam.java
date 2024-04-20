package com.elearning.enstab.exam.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String identifier;
    private String niveau;
    private String semestre ;
    private String specialite ;
    private String matiere ;
    private Integer examen ;
    private Integer cours;




    public Exam(String name, UUID identifier, String niveau, String semestre, String specialite , String matiere, Integer Examen , Integer cours) {
        this.identifier = identifier.toString();
        this.name = name;
        this.niveau = niveau;
        this.semestre=semestre;
        this.specialite=specialite;
        this.matiere=matiere;
        this.examen=Examen;
        this.cours=cours;
    }



}