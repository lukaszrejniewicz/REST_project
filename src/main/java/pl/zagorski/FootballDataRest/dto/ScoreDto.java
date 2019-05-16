package pl.zagorski.FootballDataRest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ScoreDto {
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String duration;
}
