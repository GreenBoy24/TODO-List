package com.example.todo.list.search;

import lombok.Data;

/**
 * Кдасс поиска для TodoList
 * @author Пётр
 */
@Data
public class ToDoListSearch {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;
}
