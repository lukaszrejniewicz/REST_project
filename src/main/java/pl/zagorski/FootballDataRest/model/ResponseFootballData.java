package pl.zagorski.FootballDataRest.model;

import pl.zagorski.FootballDataRest.model.competition.Competition;
import pl.zagorski.FootballDataRest.model.match.Match;

import java.util.ArrayList;
import java.util.List;

public class ResponseFootballData {
    private Competition competition;
    List<Match> matches = new ArrayList<>();

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
