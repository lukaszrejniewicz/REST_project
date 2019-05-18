package pl.zagorski.FootballDataRest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
public class ScoreWebDto {
    private int id;
    private int homeTeamGoals;
    private int awayTeamGoals;
    private String duration;

    @Tolerate
    public ScoreWebDto() {
    }
}
