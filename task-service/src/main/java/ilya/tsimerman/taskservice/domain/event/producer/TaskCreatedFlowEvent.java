package ilya.tsimerman.taskservice.domain.event.producer;

import lombok.Builder;

import java.time.Instant;

@Builder
public record TaskCreatedFlowEvent(
        Long taskId,
        Instant createdAt
) {}
