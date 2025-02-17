package com.project.minor1.repository;

import com.project.minor1.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query(value = "Select * from author where email =:email", nativeQuery = true) //-> native = ture hibernate will not look into the query
    Author getAuthor(String email);                                                //-> first way of writing query directly mysql will run query

    // 2nd way of writing the query
    //
   /* @Query(value = "select a form Author where a.email =:email") //nativeQuery = false hibernate will take care of it
    Author getAuthorWithoutNative(String email);*/

    // 3rd way of writing the query JPQL
    Author findByEmail(String email); // we have to follow some rules(way of writing method name) and hibernate will create query
    //
    //
    //Author findByEmailAndName(......)
}
