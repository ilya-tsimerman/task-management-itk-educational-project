package ilya.tsimerman.accountingservice.service;

import ilya.tsimerman.accountingservice.domain.event.consumer.TaskStreamEvent;

import java.math.BigDecimal;
import java.util.UUID;

public interface AccountService {
    BigDecimal getBalanceByUserId(UUID id);
    void addAccount(UUID id);
    void addCompletedTask(TaskStreamEvent taskStreamEventDto);
}
