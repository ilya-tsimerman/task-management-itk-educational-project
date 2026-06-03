package ilya.tsimerman.accountingservice.domain.event.consumer;

import ilya.tsimerman.accountingservice.domain.data.common.TaskStatus;

import java.util.UUID;

public record TaskStreamEvent(
        Long id,
        TaskStatus status,
        UUID assigneeId
) {}
