package com.misbah.authorsapp.repository;

import com.misbah.authorsapp.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Long>
{
    List<Author> findByNameContainingIgnoreCase(String name);
    List<Author> findByAgeGreaterThan(int age);
    Author findTopByOrderByAgeAsc();
    Author findTopByOrderByAgeDesc();

    @Query("SELECT a FROM Author a WHERE LOWER(a.name)LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Author> searchByName(@Param("keyword") String keyword);

}
