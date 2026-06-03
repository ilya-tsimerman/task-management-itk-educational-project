package ilya.tsimerman.accountingservice.domain.event.consumer;

import java.time.Instant;

public record UserCreatedFlowEvent(
        String userId,
        Instant createdAt
) {}
