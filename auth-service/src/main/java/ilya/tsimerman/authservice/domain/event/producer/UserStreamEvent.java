package ilya.tsimerman.authservice.domain.event.producer;

public record UserStreamEvent(
        String id,
        String name
) {}
