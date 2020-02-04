package com.in28minutes.rest.webservices.restfulwebservices.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository// Bean that communicates with DB (H2)
public interface UserRepository extends JpaRepository<User, Integer> {

    /*Optional<User> findByUserName(String userName);*/


}
