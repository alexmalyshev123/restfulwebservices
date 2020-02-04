/*
package com.in28minutes.rest.webservices.restfulwebservices.helloWorld;

import com.in28minutes.rest.webservices.restfulwebservices.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserResource {


    @Autowired
    private UserDaoService service;


    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){

        User user = service.deleteById(id);

        if(user == null){
            throw new RuntimeException("id not found" + id);
        }
    }


    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id){

        User user = service.findOne(id);

        if(user == null){
            throw new RuntimeException("id-" + id);
        }

        // HATEOAS: Adds links to our resource (link method retrieveAllUsers to our resource ../id)
        Resource<User> resource = new Resource<User>(user);

        // Now we will add links to this resource
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());//created link to a method
        resource.add(linkTo.withRel("all-users")); //adding link to our resource with a name all-users
        return resource;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){ // maps values from post request to User Object
        User savedUser = service.save(user);

        //CREATED (return status back - uri of the created resource (reponse) )
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); //concatinates cuurent path (user) with Id of the added user
        return ResponseEntity.created(location).build();

    }













}
*/
