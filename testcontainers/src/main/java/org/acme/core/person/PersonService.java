package org.acme.core.person;

import org.acme.core.utils.EmailUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

@ApplicationScoped
public class PersonService {

    @Inject
    PersonRepository personRepository;

    public Person findByName(String name){
        return personRepository.findByName(name).orElseThrow(NotFoundException::new);
    }

    public Person savePerson(Person person){
        if (EmailUtils.isValidEmail(person.getEmail()))
            return personRepository.savePerson(person);
        throw new IllegalArgumentException("Invalid Email");
    }



}
