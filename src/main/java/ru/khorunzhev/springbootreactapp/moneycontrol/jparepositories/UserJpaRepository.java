package ru.khorunzhev.springbootreactapp.moneycontrol.jparepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.khorunzhev.springbootreactapp.moneycontrol.entites.Consumption;
import ru.khorunzhev.springbootreactapp.moneycontrol.entites.ConsumtionCategory;
import ru.khorunzhev.springbootreactapp.moneycontrol.entites.User;

import java.util.Date;
import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
