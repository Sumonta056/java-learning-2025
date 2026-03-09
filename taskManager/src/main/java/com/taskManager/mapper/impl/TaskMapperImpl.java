package com.taskManager.mapper.impl;

import com.taskManager.domain.CreateTaskRequest;
import com.taskManager.domain.dto.CreateTaskRequestDTO;
import com.taskManager.domain.dto.TaskDTO;
import com.taskManager.domain.entity.Task;
import com.taskManager.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public CreateTaskRequest fromDto(CreateTaskRequestDTO dto) {
        return new CreateTaskRequest(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority()
        );
    }

    @Override
    public TaskDTO toDto(Task task) {
        return new TaskDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}
