package ilya.tsimerman.accountingservice.domain.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "completed_tasks")
public class CompletedTask {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "assignee_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_completed_tasks_account")
    )
    private Account account;

    @Column(name = "reward_paid", nullable = false)
    private boolean rewardPaid = false;

}