package ilya.tsimerman.accountingservice.service.impl;

import ilya.tsimerman.accountingservice.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewardScheduler {
    private final RewardService rewardService;

    @Scheduled(fixedRate = 14L * 24 * 60 * 60 * 1000)
    public void processRewards() {
        rewardService.calculateAndPayRewards();
    }
}
