package ilya.tsimerman.accountingservice.domain.repository;

import ilya.tsimerman.accountingservice.domain.data.model.CompletedTask;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedTaskRepository extends JpaRepository<CompletedTask, Long> {
    @EntityGraph(attributePaths = {"account"})
    List<CompletedTask> findAllByRewardPaidFalse();
}
