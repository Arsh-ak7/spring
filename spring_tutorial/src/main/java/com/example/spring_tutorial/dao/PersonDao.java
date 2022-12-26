package com.example.spring_tutorial.dao;

import com.example.spring_tutorial.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public interface PersonDao {
    int insertPerson(UUID id, Person person);

    default  int insertPerson(Person person) {
        UUID id = UUID.randomUUID();
        return (insertPerson(id, person));
    }

    List<Person> selectAllPeople();

    Optional<Person> getPersonById(UUID id);

    int deletePersonById(UUID id);

    int updatePersonById(UUID id, Person person);
}
