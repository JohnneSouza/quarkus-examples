package org.acme.core.integration;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.core.config.MongoResource;
import org.acme.core.person.Person;
import org.acme.core.person.PersonRepository;
import org.acme.core.person.PersonService;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@QuarkusTestResource(MongoResource.class)
class IntegrationTest {

    @Inject
    PersonService personService;

    @Inject
    PersonRepository personRepository;

    @Test
    void shouldSavePersonToDatabase(){
        this.personService.savePerson(randomPerson());

        Person foundPerson = this.personService.findByName(randomPerson().getName());

        assertNotNull(foundPerson.id);
        assertEquals("Pedro", foundPerson.getName());
    }


    private Person randomPerson(){
        Person person = new Person();
        person.setName("Pedro");
        person.setEmail("pedro@gmail.com");
        return person;
    }

}
