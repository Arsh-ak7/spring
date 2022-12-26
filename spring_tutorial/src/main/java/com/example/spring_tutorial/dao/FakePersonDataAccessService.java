package com.example.spring_tutorial.dao;

import com.example.spring_tutorial.model.Person;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person){
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople(){
        return DB;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return getPersonById(id).map(p -> {
            int indexOfPerson = DB.indexOf(p);
            if(indexOfPerson >= 0) {
                DB.set(indexOfPerson, new Person(id, person.getName()));
                return 1;
            } else {
                return 0;
            }
        }).orElse(0);
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = getPersonById(id);
        if(person.isEmpty()){
            return 0;
        } else {
            DB.remove(person.get());
            return 1;
        }
    }
}
