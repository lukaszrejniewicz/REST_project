package pl.zagorski.FootballDataRest.dto;

import pl.zagorski.FootballDataRest.model.entities.TeamEntity;

public class EntityMapper {

    public static TeamWebDto getTeamWebDto (final TeamEntity teamEntity) {
        final TeamWebDto result = TeamWebDto.builder()
                .id(teamEntity.getId())
                .name(teamEntity.getName())
                .build();
        return result;
    }
}
