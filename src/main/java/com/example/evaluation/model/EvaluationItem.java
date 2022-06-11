package com.example.evaluation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EvaluationItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotEmpty
    @NotNull
    protected String name;
    

    @JsonIgnore
    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    protected Set<Evaluation> evaluations;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<User> users;



}
