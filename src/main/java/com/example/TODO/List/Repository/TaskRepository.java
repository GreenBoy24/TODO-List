package com.example.todo.list.repository;

import com.example.todo.list.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.UUID;

/**
 * Интерфейс класса Task, для соединения с БД
 * @author Пётр
 */
public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT p FROM Task p where " +
            "(:name is null or lower(p.name) like lower(concat('%', :name,'%'))) and" +
            "(:completed is null or p.completed=:completed) and " +
            "(:createdDate is null or p.createdDate=:createdDate) and " +
            "(:changedDate is null or p.changedDate=:changedDate) and " +
            "(:priority is null or p.priority=:priority) and " +
            "(:id is null or p.id=:id)"
    )


    Page<Task> findByParams(@Param("id") UUID id,
                            @Param("name") String name,
                            @Param("completed") boolean completed,
                            @Param("createdDate") Date createdDate,
                            @Param("changedDate") Date changedDate,
                            @Param("priority") int priority,
                            Pageable pageable
    );
}
