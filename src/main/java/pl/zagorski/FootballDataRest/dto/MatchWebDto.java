package pl.zagorski.FootballDataRest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
public class MatchWebDto {
    private int id;
    private TeamWebDto homeTeam;
    private TeamWebDto awayTeam;
    private int matchday;
    private String group;
    private ScoreWebDto scoreWebDto;

    @Tolerate
    public MatchWebDto() {
    }
}
