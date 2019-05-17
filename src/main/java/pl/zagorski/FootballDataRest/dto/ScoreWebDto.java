package pl.zagorski.FootballDataRest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class ScoreWebDto {
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String duration;
}
