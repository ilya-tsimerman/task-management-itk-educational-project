package ilya.tsimerman.taskservice.domain.data.dto;

import java.util.UUID;

public record UserDto(
        UUID id,
        String name,
        String email
) {}
