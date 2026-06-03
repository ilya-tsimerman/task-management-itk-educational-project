package ilya.tsimerman.accountingservice.service.impl;

import ilya.tsimerman.accountingservice.config.AccountingProperties;
import ilya.tsimerman.accountingservice.domain.data.model.Account;
import ilya.tsimerman.accountingservice.domain.data.model.CompletedTask;
import ilya.tsimerman.accountingservice.domain.event.producer.PaymentFlowEvent;
import ilya.tsimerman.accountingservice.domain.repository.CompletedTaskRepository;
import ilya.tsimerman.accountingservice.service.RewardService;
import ilya.tsimerman.accountingservice.service.kafka.AccountEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RewardServiceImpl implements RewardService {

    private final AccountingProperties accountingProperties;
    private final CompletedTaskRepository completedTaskRepository;
    private final AccountEventPublisher accountEventPublisher;

    @Override
    @Transactional
    public void calculateAndPayRewards() {

        List<CompletedTask> tasks = completedTaskRepository.findAllByRewardPaidFalse();

        tasks.stream()
                .collect(Collectors.groupingBy(t -> t.getAccount().getId()))
                .forEach((accountId, accountTasks) -> {

                    Account account = accountTasks.getFirst().getAccount();

                    BigDecimal totalReward = accountingProperties.rewardPerTask()
                            .multiply(BigDecimal.valueOf(accountTasks.size()));

                    account.setBalance(account.getBalance().add(totalReward));

                    accountTasks.forEach(t -> t.setRewardPaid(true));

                    accountEventPublisher.publish(
                            new PaymentFlowEvent(
                                    accountId,
                                    totalReward,
                                    Instant.now(),
                                    accountTasks.stream()
                                            .map(CompletedTask::getId)
                                            .toList()
                            )
                    );
                });
    }
}