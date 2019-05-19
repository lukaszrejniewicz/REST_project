package pl.zagorski.FootballDataRest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
public class MatchWebDto {
    private int id = -1;
    private TeamWebDto homeTeam = TeamWebDto.builder().build();
    private TeamWebDto awayTeam = TeamWebDto.builder().build();
    private int matchday;
    private String group;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String duration;

    @Tolerate
    public MatchWebDto() {
    }
}
