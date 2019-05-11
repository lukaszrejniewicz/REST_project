package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.dto.MatchDto;
import pl.zagorski.FootballDataRest.dto.TeamDto;
import pl.zagorski.FootballDataRest.model.match.Match;
import pl.zagorski.FootballDataRest.validation.TeamEnum;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InterestingFactsServiceImpl implements InterestingFactsService {

    @Autowired
    private FootballDataService footballDataService;

    @Override
    public Optional<MatchDto> matchWithTheMostAmountGoals(int matchday) {
        List<Match> matchList = footballDataService.getAllData().getMatches();

        Optional<MatchDto> result = matchList
                .stream()
                .filter(match -> match.getMatchday() == matchday)
                .reduce((match, match2) -> {
                    if (countAllGoals(match) > countAllGoals(match2)) {
                        return match;
                    } else {
                        return match2;
                    }
                })
                .filter(match -> match.getScore().getWinner() != null)
                .map(this::createMatchDto);
        if (!result.isPresent()) {
            throw new NoSuchElementException("Matchday o numerze: " + matchday + ", nie istnieje lub nie został jeszcze rozegrany.");
        }
        return result;
    }

    private int countAllGoals(Match match) {
        return match.getScore().getFullTime().getAwayTeam() + match.getScore().getFullTime().getHomeTeam();
    }

    private MatchDto createMatchDto(Match match) {
        MatchDto matchDto = new MatchDto.MatchDtoBuilder()
                .matchday(match.getMatchday())
                .winnerTeam(specifyTheWinner(match))
                .homeTeam(match.getHomeTeam().getName())
                .awayTeam(match.getAwayTeam().getName())
                .goals(countAllGoals(match))
                .build();
        return matchDto;
    }

    private String specifyTheWinner(Match match) {
        String result;
        if (match.getScore().getWinner().equals("HOME_TEAM")) {
            result = match.getHomeTeam().getName();
        } else if (match.getScore().getWinner().equals("AWAY_TEAM")) {
            result = match.getAwayTeam().getName();
        } else {
            result = "WYNIK REMISOWY";
        }
        return result;
    }

    @Override
    public List<MatchDto> matchesWithTheGivenTeam(String name) {
        TeamEnum.checkTeam(name);
        List<Match> matchList = footballDataService.getAllData().getMatches();
        List<MatchDto> result = matchList
                .stream()
                .filter(match -> match.getHomeTeam().getName().equals(name) || match.getAwayTeam().getName().equals(name))
                .filter(match -> match.getScore().getWinner() != null)
                .map(this::createMatchDto)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public List<MatchDto> winningMatchesWithAGivenTeam(String name) {
        TeamEnum.checkTeam(name);
        List<MatchDto> result = matchesWithTheGivenTeam(name)
                .stream()
                .filter(matchDto -> matchDto.getWinnerTeam().equals(name))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Optional<TeamDto> teamWithTheMostVictories() {
        List<MatchDto> matches = footballDataService.getAllData().getMatches()
                .stream()
                .filter(match -> match.getScore().getWinner() != null)
                .map(this::createMatchDto).collect(Collectors.toList());

        Optional<TeamDto> result = countVictories(matches)
                .entrySet()
                .stream()
                .filter(stringIntegerEntry -> (!stringIntegerEntry.getKey().equals("WYNIK REMISOWY")))
                .max((o1, o2) -> o1.getValue() > o2.getValue() ? 1 : o1.getValue() < o2.getValue() ? -1 : 0)
                .map(stringIntegerEntry -> {
                    TeamDto teamDto = new TeamDto();
                    teamDto.setName(stringIntegerEntry.getKey());
                    teamDto.setNumberOfWins(stringIntegerEntry.getValue());
                    return teamDto;
                });
        return result;
    }

    private Map<String, Integer> countVictories(List<MatchDto> matches) {

        Map<String, Integer> map = new HashMap<>();

        for (MatchDto match : matches) {
            if (map.containsKey(match.getWinnerTeam())) {
                map.put(match.getWinnerTeam(), map.get(match.getWinnerTeam()) + 1);
            } else {
                map.put(match.getWinnerTeam(), 1);
            }
        }
        return map;
    }

    @Override
    public Optional<TeamDto> teamWithTheMostVictoriesInARow() {
        List<Match> matches = footballDataService.getAllData().getMatches();
        List<MatchDto> matchDtoList = matches
                .stream()
                .filter(match -> match.getScore().getWinner() != null)
                .map(this::createMatchDto)
                .collect(Collectors.toList());

        Optional<TeamDto> result = countVictoriesInARow(matchDtoList)
                .entrySet()
                .stream()
                .max((o1, o2) -> o1.getValue() > o2.getValue() ? 1 : o2.getValue() > o1.getValue() ? -1 : 0)
                .map(stringIntegerEntry -> {
                    TeamDto teamDto = new TeamDto();
                    teamDto.setName(stringIntegerEntry.getKey());
                    teamDto.setNumberOfWins(stringIntegerEntry.getValue());
                    return teamDto;
                });
        return result;
    }

    private Map<String, Integer> countVictoriesInARow(List<MatchDto> matches) {
        Set<String> allTeams = new HashSet<>();
        int count;

        for (MatchDto match : matches) {
            if (!allTeams.contains(match.getHomeTeam())) {
                allTeams.add(match.getHomeTeam());
            } else if (!allTeams.contains(match.getAwayTeam())) {
                allTeams.add(match.getAwayTeam());
            }
        }

        Map<String, Integer> map = new TreeMap<>();
        for (String team : allTeams) {
            count = 0;
            for (MatchDto match : matches) {
                if (team.equals(match.getHomeTeam()) || team.equals(match.getAwayTeam())) {
                    if (team.equals(match.getWinnerTeam())) {
                        count++;
                    } else {
                        if (!map.containsKey(team) || count > (map.get(team))) {
                            map.put(team, count);
                        }
                        count = 0;
                    }
                }
            }
        }
        return map;
    }
}
