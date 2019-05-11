package pl.zagorski.FootballDataRest.service;

import pl.zagorski.FootballDataRest.dto.MatchDto;
import pl.zagorski.FootballDataRest.dto.TeamDto;

import java.util.List;
import java.util.Optional;

public interface InterestingFactsService {
    /**
     * 1.
     * Metoda,która zwróci mecz w którym padło najwięcej goli w wybranej kolejce spotkań(matchday).
     * W przypadku wystąpienia kilku spotkań z tą samą liczbą bramek zwróci pierwszy największy napotkany rezultat.
     */
    Optional<MatchDto> matchWithTheMostAmountGoals(int matchday);

    /**
     * 2.
     * Metoda, która zwróci listę wszystkich meczy w których brała udział drużyna o wskazanej nazwie.
     */
    List<MatchDto> matchesWithTheGivenTeam(String name);

    /**
     * 3.
     * Metoda, która zwróci listę zwycięskich meczy drużyny o wskazanej nazwie.
     */
    List<MatchDto> winningMatchesWithAGivenTeam(String name);

    /**
     * 4.
     * Metoda, która zwróci drużynę, która odniosła w tym sezonie największą liczbę zwycięstw.
     */
    Optional<TeamDto> teamWithTheMostVictories();

    /**
     * 5.
     * Metoda, która zwróci drużynę, która odniosła w tym sezonie największą liczbę zwycięstw z rzędu.
     */
    Optional<TeamDto> teamWithTheMostVictoriesInARow();

}


