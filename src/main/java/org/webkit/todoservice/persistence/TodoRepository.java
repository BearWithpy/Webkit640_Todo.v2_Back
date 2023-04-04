package org.webkit.todoservice.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.webkit.todoservice.domain.TodoEntity;

import java.util.List;


@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    @Query("select t from TodoEntity t where t.userId = ?1")
    List<TodoEntity> searchByUserId(String userId);

    List<TodoEntity> findByUserId(String userId);
}
