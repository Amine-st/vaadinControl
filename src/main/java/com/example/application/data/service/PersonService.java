package com.example.application.data.service;

import com.example.application.data.entity.Person;
import com.example.application.data.repository.PersonRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;

@Service
public class PersonService   extends CrudService<Person, Integer>{

    public static final int PersonS_COUNT_LIMIT = 1000;



    public static class LimitReached extends RuntimeException {
    }

    private final PersonRepository personRepository;
    @Override
    protected JpaRepository<Person, Integer> getRepository() {
        return personRepository;
    }
    public PersonService(PersonRepository PersonRepository) {
        this.personRepository = PersonRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public int countAll() {
        return (int) personRepository.count();
    }

    public List<Person> findByNameContainingIgnoreCase(String name) {
        return personRepository.findByNameContainingIgnoreCase(name);
    }

    public Person save(Person Person) {
        if (countAll() >= PersonS_COUNT_LIMIT) {
            throw new LimitReached();
        }

        return personRepository.save(Person);
    }

    public void delete(Person Person) {
        personRepository.delete(Person);
    }
}
