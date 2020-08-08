package ru.khorunzhev.springbootreactapp.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.khorunzhev.springbootreactapp.todo.Todo;

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

    @DeleteMapping("users/{name}/todos/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable String name,
                                           @PathVariable long id) {
        Todo deleted = toDoService.delete(id);
        if (deleted != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
