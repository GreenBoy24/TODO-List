package com.example.todo.list.services;

import com.example.todo.list.entity.ToDoList;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс класса ToDoList, для сервиса
 * @author Пётр
 */
public interface ServiceForToDoList {

    /**
     * Добавляет новый список дел
     * @param toDoList - список дел для создания
     * @return - добавленный список дел
     */
    ToDoList add(ToDoList toDoList);

    /**
     * Обновляет список дел
     * @param toDoList - список дел в соответсвии с которым нужно обновить данные
     * @return - добавленный список дел
     */
    boolean update(ToDoList toDoList);

    /**
     * Удаляет список дел с заданным ID
     * @param id - id списка дел, который нужно удалить
     */
    boolean deleteById(UUID id);

    /**
     * Возвращает список дел по заданному ID
     * @param id - ID списка дел
     * @return - объект списка дел с заданным ID
     */
    ToDoList findById(UUID id);

    /**
     * Возвращает списки всех имеющихся дел
     * @return список списка заданий
     */
    List<ToDoList> findAll();
}
