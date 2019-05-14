package pl.zagorski.FootballDataRest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MatchFormDto {
    private int id;

    private int homeTeamId;

    private int awayTeamId;

    private String homeTeam;
    private String awayTeam;
}
