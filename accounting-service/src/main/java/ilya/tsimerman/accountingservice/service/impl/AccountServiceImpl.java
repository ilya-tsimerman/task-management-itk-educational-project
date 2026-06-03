package ilya.tsimerman.accountingservice.service.impl;

import ilya.tsimerman.accountingservice.domain.data.mapper.CompletedTaskMapper;
import ilya.tsimerman.accountingservice.domain.data.model.Account;
import ilya.tsimerman.accountingservice.domain.data.model.CompletedTask;
import ilya.tsimerman.accountingservice.domain.event.consumer.TaskStreamEvent;
import ilya.tsimerman.accountingservice.domain.repository.AccountRepository;
import ilya.tsimerman.accountingservice.domain.repository.CompletedTaskRepository;
import ilya.tsimerman.accountingservice.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CompletedTaskRepository completedTaskRepository;
    private final CompletedTaskMapper completedTaskMapper;

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getBalanceByUserId(UUID id) {
        return accountRepository.findProjectedById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден %s".formatted(id)))
                .getBalance();
    }

    @Override
    @Transactional
    public void addAccount(UUID id) {
        Account account = new Account();
        account.setId(id);
        account.setBalance(BigDecimal.ZERO);

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void addCompletedTask(TaskStreamEvent taskStreamEventDto) {
        Account account = accountRepository.findById(taskStreamEventDto.assigneeId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Аккаунт исполнителя %s задачи %s не зарегистрирован"
                                .formatted(taskStreamEventDto.assigneeId(), taskStreamEventDto.id())
                ));
        CompletedTask task = completedTaskMapper.toEntity(taskStreamEventDto);
        task.setAccount(account);

        completedTaskRepository.save(task);
    }
}
