package com.exam.speech.database.persistence.repository;

import com.exam.speech.database.persistence.entity.Author;
import com.exam.speech.database.persistence.entity.Speech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Integer> {

    Speech findById(int id);

    List<Speech> findByAuthorIn(List<Author> authorList);

    List<Speech> findByContentLike(String content);

    List<Speech> findBySpeechDateBetween(Date startDate, Date endDate);

    List<Speech> findByKeywordsLike(String keyword);

    @Query("SELECT a FROM Speech a " +
            "LEFT JOIN Author b " +
            "ON b.id = a.author " +
            "WHERE CONCAT (a.content, ' ', a.keywords, ' ', " +
            "b.firstName, ' ', b.lastName) " +
            "LIKE %:searchWord% ")
    List<Speech> findByGenericSearch(@Param("searchWord") String searchWord);
}
