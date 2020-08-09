package ru.khorunzhev.springbootreactapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.khorunzhev.springbootreactapp.todo.Todo;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ToDoResource {

    @Autowired
    private ToDoHardCodedService toDoService;

    @GetMapping("users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName) {
        return toDoService.findAll();
    }

    @GetMapping("users/{userName}/todos/{id}")
    public Todo getToDo(@PathVariable String userName, @PathVariable long id) {
        return toDoService.findById(id).get();
    }

    @DeleteMapping("users/{name}/todos/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable String name,
                                           @PathVariable long id) {
        Todo deleted = toDoService.delete(id);
        if (deleted != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("users/{name}/todos/{id}")
    public ResponseEntity<Todo> updateToDo(@PathVariable String name,
                                           @PathVariable long id, @RequestBody Todo todo) {
        Todo toDoUpdated = toDoService.save(todo);
        return new ResponseEntity<Todo>(toDoUpdated, HttpStatus.OK);

    }

    @PostMapping("users/{name}/todos")
    public ResponseEntity<Void> updateToDo(@PathVariable String name,
                                           @RequestBody Todo todo) {
        Todo createdTodo = toDoService.save(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
