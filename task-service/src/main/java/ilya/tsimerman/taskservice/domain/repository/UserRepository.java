package ilya.tsimerman.taskservice.domain.repository;

import ilya.tsimerman.taskservice.domain.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {
}
