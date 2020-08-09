package ru.khorunzhev.springbootreactapp.todo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoHardCodedService {

    private static List<Todo> todoList = new ArrayList();
    private static long idCounter;

    static {
        todoList.add(new Todo(idCounter++, "lox", "1", new Date(), false));
        todoList.add(new Todo(idCounter++, "lox", "2", new Date(), true));
        todoList.add(new Todo(idCounter++, "lox", "3", new Date(), false));
        todoList.add(new Todo(idCounter++, "lox", "4", new Date(), true));
        todoList.add(new Todo(idCounter++, "lox", "5", new Date(), false));
    }

    public List<Todo> findAll() {
        return todoList;
    }

    public Todo delete(long id) {
        if (findById(id).isPresent()) {
            Todo todo = findById(id).get();
            todoList.remove(todo);
            return todo;
        }
        return null;
    }

    public Optional<Todo> findById(long id) {
        return todoList.stream().filter((Todo todo) -> todo.getId().equals(id))
                .findFirst();
    }

    public Todo save(Todo todo) {
        if (todo.getId() == -1) {
            todo.setId(idCounter++);
            todoList.add(todo);
        } else {
            delete(todo.getId());
            todoList.add(todo);
        }
        return todo;
    }
}
