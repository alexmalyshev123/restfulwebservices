package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.in28minutes.rest.webservices.restfulwebservices.Post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {


    @Autowired // Using Entity Manager to communicate with DB
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String welcome(){
        return "Welcome to everyone";
    }

    @GetMapping("/admin/users")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){

        userRepository.deleteById(id); // if method fails it throws an exception

    }


    @GetMapping("/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id){

        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new RuntimeException("id-" + id);
        }

        // HATEOAS: Adds links to our resource (link method retrieveAllUsers to our resource ../id)
        Resource<User> resource = new Resource<User>(user.get());

        // Now we will add links to this resource
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());//created link to a method
        resource.add(linkTo.withRel("all-users")); //adding link to our resource with a name all-users
        return resource;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){ // maps values from post request to User Object
        User savedUser = userRepository.save(user);

        //CREATED (return status back - uri of the created resource (reponse) )
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri(); //concatinates cuurent path (user) with Id of the added user
        return ResponseEntity.created(location).build();

    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id){

        Optional<User>  userOptional =  userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new RuntimeException("id not found- " + id);
        }

        return  userOptional.get().getPosts();
    }

    @PostMapping(value = "/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){ // maps values from post request to User Object

        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent()){
            throw new RuntimeException("id not found- " + id);
        }

        User user = userOptional.get();
        post.setUser(user);

        postRepository.save(post); // saving post to Post table in DB

        //CREATED (return status back - uri of the created resource (reponse) )
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri(); //concatinates cuurent path (user) with Id of the added user
        return ResponseEntity.created(location).build();

    }

















}
