package pl.zagorski.FootballDataRest.dto;

import pl.zagorski.FootballDataRest.model.entities.TeamEntity;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class MatchFormDto {
    private int id;

    private int homeTeamId;

    private int awayTeamId;
}
