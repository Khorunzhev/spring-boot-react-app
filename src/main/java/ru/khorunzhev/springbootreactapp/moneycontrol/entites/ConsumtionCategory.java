package ru.khorunzhev.springbootreactapp.moneycontrol.entites;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class ConsumtionCategory {

    @Id
    @GeneratedValue
    private String id;
    private String categorieName;

    @OneToMany(mappedBy="consumptionCategory")
    private Set<Consumption> consumptions;



}
