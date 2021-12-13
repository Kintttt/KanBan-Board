package com.week9.taskmanagerapp.respository;

import com.week9.taskmanagerapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
boolean existsByEmailAndPassword(String email, String password);
Person findByEmail(String email);


}
