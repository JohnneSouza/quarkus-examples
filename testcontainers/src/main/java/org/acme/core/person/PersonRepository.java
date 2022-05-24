package org.acme.core.person;

import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class PersonRepository implements PanacheMongoRepositoryBase<Person, String> {

    public Optional<Person> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public Person savePerson(Person person){
        if (person.id == null)
            person.id = UUID.randomUUID().toString();
        persist(person);
        return person;
    }

}
