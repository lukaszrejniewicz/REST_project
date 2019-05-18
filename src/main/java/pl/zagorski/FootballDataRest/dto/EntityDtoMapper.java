package pl.zagorski.FootballDataRest.dto;

import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.model.entities.ScoreEntity;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;

public class EntityDtoMapper {

    public static TeamWebDto getTeamWebDto(final TeamEntity teamEntity) {
        final TeamWebDto result = TeamWebDto.builder()
                .id(teamEntity.getId())
                .name(teamEntity.getName())
                .build();
        return result;
    }

    public static ScoreWebDto getScoreWebDto(final ScoreEntity scoreEntity) {
        final ScoreWebDto result = ScoreWebDto.builder()
                .id(scoreEntity.getId())
                .homeTeamGoals(scoreEntity.getHomeTeamScore())
                .awayTeamGoals(scoreEntity.getAwayTeamScore())
                .duration(scoreEntity.getDuration())
                .build();
        return result;
    }

    public static MatchWebDto getMatchWebDto(final MatchEntity matchEntity) {
        MatchWebDto result = MatchWebDto.builder()
                .id(matchEntity.getId())
                .homeTeam(getTeamWebDto(matchEntity.getHomeTeam()))
                .awayTeam(getTeamWebDto(matchEntity.getAwayTeam()))
                .matchday(matchEntity.getMatchday())
                .group(matchEntity.getGroup())
//                .scoreWebDto(getScoreWebDto(matchEntity.getScore()))
                .build();
        return result;
    }


//    public static TeamEntity getTeamEntity(final TeamWebDto teamWebDto) {
//        final TeamEntity result = TeamEntity.builder()
//                .id(teamWebDto.getId())
//                .name(teamWebDto.getName())
//                .build();
//        return result;
//    }
//
//    public static MatchEntity getMatchEntity(final MatchWebDto matchWebDto) {
//        final MatchEntity result = MatchEntity.builder()
//                .id(matchWebDto.getId())
//                .homeTeam(getTeamEntity(matchWebDto.getHomeTeam()))
//    }
}
