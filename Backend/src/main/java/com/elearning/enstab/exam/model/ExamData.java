package com.elearning.enstab.exam.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamData {

    @JsonProperty("niveau")
    public String niveau;
    @JsonProperty("semestre")
    public Integer semestre ;
    @JsonProperty("specialite")
    public String specialite ;
    @JsonProperty("matiere")
    public String matiere ;
    @JsonProperty("examen")
    public Integer examen ;
    @JsonProperty("cours")
    public Integer cours;
}