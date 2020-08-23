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
    public List<Consumption> getAllUserConsumptions(@PathVariable String login) {
        User user = userJpaRepository.findByLogin(login);
        return consumptionJpaRepository.findByUser(user);
    }

    @GetMapping("jpa/users/{login}/consumptions/{id}")
    public Consumption getConsumption(@PathVariable String login, @PathVariable long id) {
        return consumptionJpaRepository.findById(id).get();
    }

    @DeleteMapping("jpa/users/{login}/consumptions/{id}")
    public ResponseEntity<Void> deleteConsumption(@PathVariable String login,
                                                  @PathVariable long id) {
        consumptionJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("jpa/users/{login}/consumptions/{id}")
    public ResponseEntity<Consumption> updateToDo(
            @PathVariable String login,
            @PathVariable long id, @RequestBody Consumption consumption) {
        Consumption updatedConsumption = consumptionJpaRepository.save(consumption);
        return new ResponseEntity<Consumption>(updatedConsumption, HttpStatus.OK);

    }

    @PostMapping("jpa/users/{login}/consumptions")
    public ResponseEntity<Void> updateToDo(@PathVariable String login,
                                           @RequestBody Consumption consumption) {
        User user = userJpaRepository.findByLogin(login);
        consumption.setUser(user);
        Consumption createdConsumption = consumptionJpaRepository.save(consumption);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdConsumption.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }
}
