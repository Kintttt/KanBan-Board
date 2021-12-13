package com.week9.taskmanagerapp.service.serviceImpl;

import com.week9.taskmanagerapp.model.Person;
import com.week9.taskmanagerapp.respository.PersonRepository;
import com.week9.taskmanagerapp.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonServices {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void savePersons(Person person){
        personRepository.save(person);
    }

    public boolean validatePerson(String email, String password) {
        return personRepository.existsByEmailAndPassword(email, password);
    }

    public Person getUserByEmail(String email){
        return personRepository.findByEmail(email);
    }

}
