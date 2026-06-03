package ilya.tsimerman.taskservice.service;

import ilya.tsimerman.taskservice.domain.data.dto.UserDto;

public interface UserService {
    void addTask(UserDto request);
}
