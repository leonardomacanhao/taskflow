package com.joao.taskflow.taskflowbackend.repository;

import com.joao.taskflow.taskflowbackend.model.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByTaskListIdOrderByPositionAsc(Long taskListId);
}