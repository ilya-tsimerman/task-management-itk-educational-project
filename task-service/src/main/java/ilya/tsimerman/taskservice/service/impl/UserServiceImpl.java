package ilya.tsimerman.taskservice.service.impl;

import ilya.tsimerman.taskservice.domain.data.dto.UserDto;
import ilya.tsimerman.taskservice.domain.data.mapper.UserMapper;
import ilya.tsimerman.taskservice.domain.data.model.User;
import ilya.tsimerman.taskservice.domain.repository.UserRepository;
import ilya.tsimerman.taskservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void addTask(UserDto request) {
        User user = userMapper.toEntity(request);
        userRepository.save(user);
    }
}
