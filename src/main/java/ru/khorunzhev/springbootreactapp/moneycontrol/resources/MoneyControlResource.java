package ru.khorunzhev.springbootreactapp.moneycontrol.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.khorunzhev.springbootreactapp.moneycontrol.entites.Consumption;
import ru.khorunzhev.springbootreactapp.moneycontrol.entites.User;
import ru.khorunzhev.springbootreactapp.moneycontrol.jparepositories.ConsumptionJpaRepository;
import ru.khorunzhev.springbootreactapp.moneycontrol.jparepositories.UserJpaRepository;
import ru.khorunzhev.springbootreactapp.todo.Todo;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MoneyControlResource {

    @Autowired
    private ConsumptionJpaRepository consumptionJpaRepository;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping("jpa/users/{login}/consumptions")
    public List<Consumption> getAllTodos(@PathVariable String login) {
        User user = userJpaRepository.findByLogin(login);
        return consumptionJpaRepository.findByUser(user);
    }

    @GetMapping("jpa/users/{userName}/todos/{id}")
    public Todo getToDo(@PathVariable String userName, @PathVariable long id) {
        return consumptionJpaRepository.findById(id).get();
    }

    @DeleteMapping("jpa/users/{name}/todos/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable String name,
                                           @PathVariable long id) {
        consumptionJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("jpa/users/{name}/todos/{id}")
    public ResponseEntity<Todo> updateToDo(@PathVariable String name,
                                           @PathVariable long id, @RequestBody Todo todo) {
        todo.setUserName(name);
        Todo toDoUpdated = consumptionJpaRepository.save(todo);
        return new ResponseEntity<Todo>(toDoUpdated, HttpStatus.OK);

    }

    @PostMapping("jpa/users/{name}/todos")
    public ResponseEntity<Void> updateToDo(@PathVariable String name,
                                           @RequestBody Todo todo) {
        todo.setUserName(name);
        Todo createdTodo = consumptionJpaRepository.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
