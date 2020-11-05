package com.example.todo.list.mapper;

import com.example.todo.list.dto.ToDoListDto;
import com.example.todo.list.entity.ToDoList;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;


@Mapper(componentModel = "spring")
@ComponentScan(basePackages = {"com.example.todo.list.*"})
public interface ToDoListMapper {
    ToDoListMapper INSTANCE = Mappers.getMapper(ToDoListMapper.class);

    ToDoListDto toDTO(ToDoList toDoList);
    ToDoList toEntity(ToDoListDto toDoListDto);
}
