package ilya.tsimerman.accountingservice.service.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import ilya.tsimerman.accountingservice.domain.event.producer.PaymentFlowEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AccountEventPublisher {
    private static final String ACCOUNT_FLOW_TOPIC = "payment-flow";

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publish(PaymentFlowEvent event) {
        try {
            kafkaTemplate.send(
                    ACCOUNT_FLOW_TOPIC,
                    String.valueOf(event.userId()),
                    objectMapper.writeValueAsString(event)
            );
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
