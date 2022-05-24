package org.acme.core.component;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.core.person.Person;
import org.acme.core.person.PersonRepository;
import org.acme.core.person.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
class ComponentTest {

    @InjectMock
    PersonRepository personRepository;

    @Inject
    PersonService personService;

    @Test
    void shouldSuccessfullySaveAPerson(){
        // Arrange
        Person mock = new Person();
        mock.id = UUID.randomUUID().toString();
        mock.setEmail("email@provider.com");
        mock.setName("Fried Chips");

        Mockito.when(personRepository.savePerson(any())).thenReturn(mock);

        // Act
        Person person = personService.savePerson(mock);

        // Assert
        Assertions.assertNotNull(person.id);
        Assertions.assertNotNull(person.getName());
        Assertions.assertNotNull(person.getEmail());
    }

    @Test
    void shouldNotSuccessfullySaveAPerson(){
        // Arrange
        Person personToSave = new Person();
        personToSave.id = UUID.randomUUID().toString();
        personToSave.setEmail("email#provider.com");
        personToSave.setName("Fried Chips");

        // Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> personService.savePerson(personToSave));

        Assertions.assertEquals("Invalid Email", exception.getMessage());
    }

}
