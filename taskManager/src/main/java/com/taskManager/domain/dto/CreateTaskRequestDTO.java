package com.taskManager.domain.dto;

import com.taskManager.domain.entity.TaskPriority;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record CreateTaskRequestDTO(
        @NotBlank(message = ERROR_MESSAGE_TITLE_LENGTH)
        @Length(max = 255, message = ERROR_MESSAGE_TITLE_LENGTH)
        String title,
        @Length(max = 1000, message = ERROR_MESSAGE_DESCRIPTION_LENGTH)
        @Nullable
        String description,
        @FutureOrPresent(message = ERROR_MESSAGE_DUE_DATE_FUTURE )
        @Nullable
        LocalDate dueDate,
        @NotNull(message = ERROR_MESSAGE_TASK_PRIORITY)
        TaskPriority priority
) {
    public static final String ERROR_MESSAGE_TITLE_LENGTH = "Title must be between 1 - 255 Character";
    public static final String ERROR_MESSAGE_DESCRIPTION_LENGTH = "Description must be less than 1000 Character";
    public static final String ERROR_MESSAGE_DUE_DATE_FUTURE = "Due date must be future";
    public static final String ERROR_MESSAGE_TASK_PRIORITY ="Task must have a priority";

}

