package ilya.tsimerman.accountingservice.domain.data.mapper;

import ilya.tsimerman.accountingservice.domain.data.model.CompletedTask;
import ilya.tsimerman.accountingservice.domain.event.consumer.TaskStreamEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CompletedTaskMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "account.id", source = "assigneeId")
    @Mapping(target = "rewardPaid", constant = "false")
    CompletedTask toEntity(TaskStreamEvent event);
}
