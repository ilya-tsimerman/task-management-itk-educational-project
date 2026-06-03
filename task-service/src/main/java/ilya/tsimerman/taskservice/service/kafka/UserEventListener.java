package ilya.tsimerman.taskservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ilya.tsimerman.taskservice.domain.data.dto.UserDto;
import ilya.tsimerman.taskservice.domain.event.consumer.UserCreatedFlowEvent;
import ilya.tsimerman.taskservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserEventListener {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "user-flow",
            groupId = "task-service"
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
        userService.addTask(
                new UserDto(UUID.fromString(event.userId()),null,null)
        );
    }
}