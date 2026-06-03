package ilya.tsimerman.taskservice.domain.data.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignTaskRequest(
        @NotNull(message = "ID исполнителя обязателен.")
        UUID assigneeId
) {}
