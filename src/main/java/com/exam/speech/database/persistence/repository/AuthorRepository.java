package com.exam.speech.database.persistence.repository;

import com.exam.speech.database.persistence.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findById(int id);

    @Query("SELECT a FROM Author a " +
            "WHERE a.lastName = :authorName " +
            "OR a.firstName = :authorName")
    List<Author> findAuthorByFirstNameOrLastName(@Param("authorName") String authorName);
}
