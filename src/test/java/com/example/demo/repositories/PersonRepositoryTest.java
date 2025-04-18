package com.example.demo.repositories;

import com.example.demo.models.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    private PersonRepository personRepository;

    @Test
    @DisplayName("Save method")
    public void testSave(){
        Person person1 = new Person("Pedro", 20);
        personRepository.save(person1);
        Person savePerson1 = personRepository.save(person1);

        assertNotNull(person1.getId());
        assertEquals("Pedro", savePerson1.getName());
        assertEquals(20, savePerson1.getAge());
        System.out.println("Respuesta de BD: " + savePerson1);

    }

    @Test
    @DisplayName("List person")
    public void testFindAll(){
        List<Person> persons = List.of(
                new Person("Ana", 25),
                new Person("Carlos", 30),
                new Person("María", 28)
        );
        personRepository.saveAll(persons);
        List<Person> personsList = personRepository.findAll();
        System.out.println(personsList);
        assertEquals(23, personsList.size());
        assertEquals("Pedro", personsList.get(0).getName());
        assertEquals(20, personsList.get(0).getAge());
    }

    @Test
    @DisplayName("Delete Person")
    public void testDelete(){
        Person person1 = new Person("Pedro", 20);
        personRepository.save(person1);
        Person foundPerson = personRepository.findById(person1.getId()).orElse(null);
        assertNotNull(foundPerson);
        personRepository.delete(foundPerson);

        System.out.println(personRepository.findById(foundPerson.getId()).orElse(null));
        assertNotNull(foundPerson.getId());
    }

    @Test
    @DisplayName("Test Update")
    public void testUpdate(){
        Person person1 = new Person("Angel", 30);
        personRepository.save(person1);
        Person personUpdate = personRepository.findById(person1.getId()).orElse(null);
        assertNotNull(personUpdate);
        personUpdate.setName("Manuel");
        personUpdate.setAge(40);
        personRepository.save(personUpdate);
        assertNotNull(personRepository.findById(person1.getId()).orElse(null));
        assertEquals("Manuel", personUpdate.getName());
        assertEquals(40, personUpdate.getAge());

        System.out.println(personRepository.findById(person1.getId()).orElse(null));
    }






}
