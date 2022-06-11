package com.example.evaluation.model;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Website extends EvaluationItem {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 200)
    private String url;

    private String shortDescription;

}
