package pl.zagorski.FootballDataRest.dto;

import pl.zagorski.FootballDataRest.model.match.AwayTeam;
import pl.zagorski.FootballDataRest.model.match.HomeTeam;
import pl.zagorski.FootballDataRest.model.match.Match;

public final class ModelDtoMapper {

    public static TeamWebDto getTeamWebDto(final HomeTeam team) {
        TeamWebDto result = TeamWebDto.builder()
                .id(team.getId())
                .name(team.getName())
                .build();
        return result;
    }

    public static TeamWebDto getTeamWebDto(final AwayTeam team) {
        TeamWebDto result = TeamWebDto.builder()
                .id(team.getId())
                .name(team.getName())
                .build();
        return result;
    }

    public static MatchWebDto getMatchWebDto(final Match match) {
        MatchWebDto result = MatchWebDto.builder()
                .homeTeam(getTeamWebDto(match.getHomeTeam()))
                .awayTeam(getTeamWebDto(match.getAwayTeam()))
                .matchday(match.getMatchday())
                .group(match.getGroup())
                .duration(match.getScore().getDuration())
                .homeTeamGoals(match.getScore().getFullTime().getHomeTeam())
                .awayTeamGoals(match.getScore().getFullTime().getAwayTeam())
                .build();
        return result;
    }
}
