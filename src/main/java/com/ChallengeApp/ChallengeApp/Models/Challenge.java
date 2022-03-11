package com.ChallengeApp.ChallengeApp.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "challenge")
public class Challenge {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;

/*    @OneToMany (mappedBy = "challenge")
    private List<Question> questionList;*/


}

