package com.example.TO_DO.repository;

import com.example.TO_DO.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUserId (Long userId);
    Optional<Todo> findByIdAndUserId(Long id, Long userId);

}
