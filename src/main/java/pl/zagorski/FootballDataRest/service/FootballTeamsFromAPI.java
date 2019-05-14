package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.model.match.Match;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class FootballTeamsFromAPI {


    @Autowired
    private FootballDataServiceImpl footballDataService;

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
}
