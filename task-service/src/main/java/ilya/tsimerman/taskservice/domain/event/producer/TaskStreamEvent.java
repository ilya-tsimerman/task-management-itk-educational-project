package ilya.tsimerman.taskservice.domain.event.producer;

import ilya.tsimerman.taskservice.domain.data.common.TaskStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record TaskStreamEvent(
        Long id,
        String title,
        String description,
        TaskStatus status,
        UUID assigneeId
) {}
