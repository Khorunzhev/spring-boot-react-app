package ru.khorunzhev.springbootreactapp.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoJpaRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserName(String userName);
}
