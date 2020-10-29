package com.example.todo.list.search;

import lombok.Data;

/**
 * Кдасс поиска для Task
 * @author Пётр
 */
@Data
public class TaskSearch {
    private Integer pageNumber;
    private Integer pageSize;
    private String sortColumn;
    private String sortDirection;
}
