package ru.khorunzhev.springbootreactapp.moneycontrol.entites;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Consumption {

    @Id
    @GeneratedValue
    private Long id;
    private String note;
    private Integer amount;
    private Date date;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="category_id", nullable=false)
    private ConsumtionCategory consumptionCategory;


}
