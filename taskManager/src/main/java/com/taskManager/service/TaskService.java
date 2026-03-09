package com.taskManager.service;

import com.taskManager.domain.CreateTaskRequest;
import com.taskManager.domain.entity.Task;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
}
