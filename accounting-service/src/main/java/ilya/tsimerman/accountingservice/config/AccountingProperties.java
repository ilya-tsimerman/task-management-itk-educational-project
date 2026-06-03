package ilya.tsimerman.accountingservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "accounting")
public record AccountingProperties(
        BigDecimal rewardPerTask
) {}