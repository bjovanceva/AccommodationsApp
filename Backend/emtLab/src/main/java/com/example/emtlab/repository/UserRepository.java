package com.example.emtlab.repository;

import com.example.emtlab.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);

    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {}
    )
    @Query("select u from User u")
    List<User> fetchAll();



}
