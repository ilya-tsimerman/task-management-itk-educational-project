package ilya.tsimerman.accountingservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ilya.tsimerman.accountingservice.domain.data.common.TaskStatus;
import ilya.tsimerman.accountingservice.domain.event.consumer.TaskStreamEvent;
import ilya.tsimerman.accountingservice.domain.event.consumer.UserCreatedFlowEvent;
import ilya.tsimerman.accountingservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventListener {
    private final AccountService accountService;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "user-flow",
            groupId = "accounting-service"
    )
    public void listenUserFlow(String message) {
        UserCreatedFlowEvent event;
        try {
            event = objectMapper.readValue(message, UserCreatedFlowEvent.class);
            log.info("UserCreatedFlowEvent: {}", event);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        accountService.addAccount(UUID.fromString(event.userId()));
    }

    @KafkaListener(
            topics = "task-stream",
            groupId = "accounting-service"
    )
    public void listenTaskFlow(String message) {
        TaskStreamEvent event;
        try {
            event = objectMapper.readValue(message, TaskStreamEvent.class);
            log.info("TaskStreamEvent: {}", event);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        if(event.status() == TaskStatus.DONE && event.assigneeId() != null){
            accountService.addCompletedTask(event);
        }
    }
}