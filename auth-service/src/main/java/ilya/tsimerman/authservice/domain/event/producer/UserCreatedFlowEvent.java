package ilya.tsimerman.authservice.domain.event.producer;

import java.time.Instant;

public record UserCreatedFlowEvent(
        String userId,
        Instant createdAt
) {}
