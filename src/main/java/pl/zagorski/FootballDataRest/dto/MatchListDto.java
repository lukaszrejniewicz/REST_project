package pl.zagorski.FootballDataRest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MatchListDto {
    private int id;
    private String homeTeam;
    private String awayTeam;
    private int matchday;
    private String group;
    private ScoreDto scoreDto;
}
