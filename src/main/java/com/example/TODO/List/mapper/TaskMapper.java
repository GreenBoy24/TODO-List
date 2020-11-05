package com.example.todo.list.mapper;

import com.example.todo.list.dto.TaskDto;
import com.example.todo.list.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.ComponentScan;

@Mapper(componentModel = "spring")
@ComponentScan(basePackages = {"com.example.todo.list.*"})
public interface TaskMapper {
    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    TaskDto toDTO(Task task);
    Task toEntity(TaskDto taskDto);
}
