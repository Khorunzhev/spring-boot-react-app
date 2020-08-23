package ru.khorunzhev.springbootreactapp.moneycontrol.entites;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String token;

    @OneToMany(mappedBy="user")
    private Set<Consumption> consumptions;

}
