package com.joao.taskflow.taskflowbackend.repository;

import com.joao.taskflow.taskflowbackend.model.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, Long> {
    List<TaskList> findByBoardIdOrderByPositionAsc(Long boardId);
}