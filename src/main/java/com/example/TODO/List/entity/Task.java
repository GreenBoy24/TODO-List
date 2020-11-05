package com.example.todo.list.entity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Cущность задачи
 * @author Пётр
 */

@Entity
@Data
@Table(name = "Task")
public class Task implements Serializable {

    /** Поле идентификатора */
    @Id
    @Column(name = "id",nullable = false)
    private UUID id;

    /** Поле имени */
    @Basic
    @Column(name = "name",nullable = false,unique = true)
    private String name;

    /** Поле краткого описания */
    @Basic
    @Column(name = "title")
    private String title;

    /** Поле сделано или нет */
    @Basic
    @Column(name = "completed",nullable = false)
    private boolean completed;

    /** Поле даты создания */
    @Basic
    @Column(name = "createdDate",nullable = false)
    private Date createdDate;

    /** Поле даты изменения */
    @Basic
    @Column(name = "changedDate",nullable = false)
    private Date changedDate;

    /** Поле срочности*/
    @Basic
    @Column(name = "priority",nullable = false)
    private int priority;

    /**
     * Конструктор - создание нового объекта
     */
    public Task() {
        id = UUID.randomUUID();
        createdDate = new Date();
        changedDate = new Date();
        completed = false;
        priority = 1;
    }

    public boolean getCompleted(){
        return completed;
    }
    
}






