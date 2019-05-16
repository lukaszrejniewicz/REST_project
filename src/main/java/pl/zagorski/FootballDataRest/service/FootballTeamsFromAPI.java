package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.model.Team;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.model.match.HomeTeam;
import pl.zagorski.FootballDataRest.model.match.Match;
import pl.zagorski.FootballDataRest.repository.TeamRepository;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class FootballTeamsFromAPI {


    @Autowired
    private FootballDataServiceImpl footballDataService;

    @Autowired
    private TeamRepository teamRepository;

    public List<String> takeToList() {

        Set<String> teamName = new LinkedHashSet<>();
        List<String> teams = new ArrayList<>();

        for (Match match : footballDataService.getAllData().getMatches()) {
            teamName.add(match.getHomeTeam().getName());
        }
        for (Match match : footballDataService.getAllData().getMatches()) {
            teamName.add(match.getAwayTeam().getName());
        }

        teams.addAll(teamName);
        return teams;
    }


    public List<HomeTeam> getAll() {

        List<HomeTeam> teams = new ArrayList<>();
        for (Match match : footballDataService.getAllData().getMatches()) {
            teams.add(match.getHomeTeam());
        }
        return teams;
    }

}
