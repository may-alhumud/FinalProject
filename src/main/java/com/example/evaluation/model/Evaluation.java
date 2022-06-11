package com.example.evaluation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Min(value = -1,message = "bad vote -1")
    @Max(value = 1,message = "good vote 1")
    private Integer vote;


    @ManyToOne
    @JsonIgnore
    private EvaluationItem item;

    @ManyToOne
    @JsonIgnore
    private User user;

}
