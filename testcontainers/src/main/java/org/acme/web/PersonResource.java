package org.acme.web;

import org.acme.core.person.Person;
import org.acme.core.person.PersonService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonService personService;

    @POST
    public Person savePerson(Person person) {
        return personService.savePerson(person);
    }

    @GET
    @Path("/{name}")
    public Person findByName(@PathParam String name){
        return personService.findByName(name);
    }
}