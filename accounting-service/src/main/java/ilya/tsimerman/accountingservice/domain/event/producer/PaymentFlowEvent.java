package ilya.tsimerman.accountingservice.domain.event.producer;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record PaymentFlowEvent(
        UUID userId,
        BigDecimal amount,
        Instant date,
        List<Long> tasksId
) {}
