package com.example.todo.list.repository;

import com.example.todo.list.entity.ToDoList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

/**
 * Интерфейс класса ToDoList, для соединения с БД
 * @author Пётр
 */
public interface ToDoListRepository extends JpaRepository<ToDoList, UUID> {

    @Query("SELECT c FROM ToDoList c where " +
            "(:name is null or :name='' or lower(c.name) like lower(concat('%', :name,'%')))  " +
            "order by c.name asc")

    Page<ToDoList> findByTitle(
            Pageable pageable
    );
}
