package ilya.tsimerman.accountingservice.domain.repository;

import ilya.tsimerman.accountingservice.domain.data.model.Account;
import ilya.tsimerman.accountingservice.domain.repository.projection.AccountBalanceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<AccountBalanceProjection> findProjectedById(UUID id);
    Optional<Account> findById(UUID id);
}
