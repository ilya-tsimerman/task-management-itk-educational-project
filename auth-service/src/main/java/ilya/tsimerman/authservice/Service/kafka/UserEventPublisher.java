package ilya.tsimerman.authservice.Service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import ilya.tsimerman.authservice.domain.event.producer.UserCreatedFlowEvent;
import ilya.tsimerman.authservice.domain.event.producer.UserStreamEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserEventPublisher {
    private static final String USER_STREAM_TOPIC = "user-stream";
    private static final String USER_FLOW_TOPIC = "user-flow";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publish(UserStreamEvent event) {
        try {
            kafkaTemplate.send(
                    USER_STREAM_TOPIC,
                    event.id(),
                    objectMapper.writeValueAsString(event)
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void publish(UserCreatedFlowEvent event) {
        try {
            kafkaTemplate.send(
                    USER_FLOW_TOPIC,
                    event.userId(),
                    objectMapper.writeValueAsString(event)
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
