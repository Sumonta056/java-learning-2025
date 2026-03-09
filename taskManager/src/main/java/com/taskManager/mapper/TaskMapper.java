package com.taskManager.mapper;

import com.taskManager.domain.CreateTaskRequest;
import com.taskManager.domain.dto.CreateTaskRequestDTO;
import com.taskManager.domain.dto.TaskDTO;
import com.taskManager.domain.entity.Task;

public interface TaskMapper {

    CreateTaskRequest fromDto(CreateTaskRequestDTO dto);
    TaskDTO toDto(Task task);
}
