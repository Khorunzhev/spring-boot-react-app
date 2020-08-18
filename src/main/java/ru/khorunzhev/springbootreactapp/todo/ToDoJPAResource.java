package ru.khorunzhev.springbootreactapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoJPAResource {

    @Autowired
    private ToDoHardCodedService toDoService;

    @Autowired
    private ToDoJpaRepository toDoJpaRepository;

    @GetMapping("jpa/users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName) {
        return toDoJpaRepository.findByUserName(userName);
    }

    @GetMapping("jpa/users/{userName}/todos/{id}")
    public Todo getToDo(@PathVariable String userName, @PathVariable long id) {
        return toDoJpaRepository.findById(id).get();
    }

    @DeleteMapping("jpa/users/{name}/todos/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable String name,
                                           @PathVariable long id) {
        toDoJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("jpa/users/{name}/todos/{id}")
    public ResponseEntity<Todo> updateToDo(@PathVariable String name,
                                           @PathVariable long id, @RequestBody Todo todo) {
        todo.setUserName(name);
        Todo toDoUpdated = toDoJpaRepository.save(todo);
        return new ResponseEntity<Todo>(toDoUpdated, HttpStatus.OK);

    }

    @PostMapping("jpa/users/{name}/todos")
    public ResponseEntity<Void> updateToDo(@PathVariable String name,
                                           @RequestBody Todo todo) {
        todo.setUserName(name);
        Todo createdTodo = toDoJpaRepository.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
