package com.example.springtask_15.controller;

import com.example.springtask_15.PersonRepository;
import com.example.springtask_15.dto.Message;
import com.example.springtask_15.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;

//    private List<Person> persons = new ArrayList<>(Arrays.asList(
//            new Person(1, "Иван", "Иванович", "Иванов", LocalDate.of(1999,2,3)),
//            new Person(2, "Пётр", "Петрович", "Петров",LocalDate.of(2002,2,2)),
//            new Person(3, "Евгений", "Васильевич", "Васин",LocalDate.of(2005,4,8)),
//            new Person(4, "Максим", "Яковлевич", "Окопский",LocalDate.of(1978,6,5))
//    ));

    @GetMapping("/person")
    public Iterable<Person> getPerson(){
        return repository.findAll();
    }

    @GetMapping("/person/{id}")
    public Optional<Person> findPersonById(@PathVariable int id){
        return repository.findById(id);
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person){
        repository.save(person);
        return person;
    }

    @PostMapping("/person/{id}/message")
    public Person addMessage(@PathVariable int id, @RequestBody Message message) {
         return service.addMeesageToPerson(id, message);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person){
        HttpStatus status = repository.existsById(id) ? HttpStatus.OK : HttpStatus.CREATED;
        return new ResponseEntity(repository.save(person), status);
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable int id){
        repository.deleteById(id);
    }

    @GetMapping
    public String hello(){
        return "Hello, World!";
    }
}

