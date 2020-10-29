package com.example.todo.list.services;

import com.example.todo.list.entity.Task;

import java.util.List;
import java.util.UUID;

/**
 * Интерфейс класса Task, для сервиса
 * @author Пётр
 */
public interface ServiceForTask {

    /**
     * Добавляет новое задание
     * @param task - задание для создания
     * @return - задание записанное в список заданий
     */
    Task add(Task task);

    /**
     * Обновляет задание
     * @param task - задание в соответсвии с которым нужно обновить данные
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Task task);

    /**
     * Удаляет задание с заданным ID
     * @param id - id задания, которое нужно удалить
     */
    boolean deleteById(UUID id);

    /**
     * Возвращает задание по заданному ID
     * @param id - ID задания
     * @return - объект задания с заданным ID
     */
    Task findById(UUID id);

    /**
     * Возвращает список всех имеющихся заданий
     * @return список заданий
     */
    List<Task> findAll();
}
