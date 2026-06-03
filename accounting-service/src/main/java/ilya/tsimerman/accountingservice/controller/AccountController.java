package ilya.tsimerman.accountingservice.controller;

import ilya.tsimerman.accountingservice.domain.exception.AccessDeniedException;
import ilya.tsimerman.accountingservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}/balance")
    public ResponseEntity<BigDecimal> getBalance(
            @RequestHeader("user-roles") List<String> roles,
            @PathVariable UUID id) {
        if (!roles.contains("admin")) {
            throw new AccessDeniedException("Доступ к балансу других пользователей разрешён только admin");
        }
        return ResponseEntity.ok(accountService.getBalanceByUserId(id));
    }

    @GetMapping("/my/balance")
    public ResponseEntity<BigDecimal> getMyBalance(@RequestHeader("user-id") UUID id) {
        return ResponseEntity.ok(accountService.getBalanceByUserId(id));
    }
}
