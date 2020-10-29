package com.example.todo.list.entity;

import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Cущность задачи
 * @autor Пётр
 */

@Entity
@Data
@Table(name = "task")
public class Task {

    /** Поле идентификатора */
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "compleated",nullable = false)
    private boolean compleated;

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
    private Integer priority;

    /**
     * Конструктор - создание нового объекта
     */
    public Task() {
        id = UUID.randomUUID();
        createdDate = new Date();
        changedDate = new Date();
        compleated = false;
        priority = 0;
    }

    public boolean getCompleated(){
        return compleated;
    }
    
}
