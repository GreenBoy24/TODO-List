package com.example.todo.list.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Cущность списка задач
 * @author  Пётр
 */
@Entity
@Data
@Table(name = "ToDoList")
public class ToDoList implements Serializable {

    /** Поле идентификатора */
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    /** Поле имени */
    @Basic
    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /** Поле даты создания */
    @Basic
    @Column(name = "createdDate", nullable = false)
    private Date createdDate;

    /** Поле даты изменения */
    @Basic
    @Column(name = "changedDate",nullable = false)
    private Date changedDate;

    /**
     * Конструктор - создание нового объекта
     */
    public ToDoList() {
        id = UUID.randomUUID();
        createdDate = new Date();
        changedDate = new Date();
    }

}
