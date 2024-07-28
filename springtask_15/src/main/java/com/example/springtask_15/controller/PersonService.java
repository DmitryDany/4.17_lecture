package com.example.springtask_15.controller;

import com.example.springtask_15.PersonRepository;
import com.example.springtask_15.dto.Message;
import com.example.springtask_15.dto.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;
    public Person addMessageToPerson(int personId, Message message) {
        Person person = repository.findById(personId).get();
        message.setPerson(person);
        message.setTime(LocalDateTime.now());
        person.addMessages(message);
        return repository.save(person);
    }
}
