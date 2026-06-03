package ilya.tsimerman.taskservice.service.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ilya.tsimerman.taskservice.domain.event.producer.TaskCreatedFlowEvent;
import ilya.tsimerman.taskservice.domain.event.producer.TaskStreamEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskEventPublisher {
    private static final String TASK_STREAM_TOPIC = "task-stream";
    private static final String TASK_FLOW_TOPIC = "task-flow";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publish(TaskCreatedFlowEvent event) {
        try {
            kafkaTemplate.send(
                    TASK_FLOW_TOPIC,
                    String.valueOf(event.taskId()),
                    objectMapper.writeValueAsString(event)
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void publish(TaskStreamEvent event) {
        try {
            kafkaTemplate.send(
                    TASK_STREAM_TOPIC,
                    String.valueOf(event.id()),
                    objectMapper.writeValueAsString(event)
            );
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
