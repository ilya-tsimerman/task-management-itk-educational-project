package ilya.tsimerman.accountingservice.domain.repository.projection;

import java.math.BigDecimal;

public interface AccountBalanceProjection {
    BigDecimal getBalance();
}
