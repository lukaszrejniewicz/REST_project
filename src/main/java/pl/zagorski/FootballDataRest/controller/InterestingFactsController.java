package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.zagorski.FootballDataRest.dto.MatchDto;
import pl.zagorski.FootballDataRest.dto.TeamDto;
import pl.zagorski.FootballDataRest.service.InterestingFactsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/interestingFacts")
public class InterestingFactsController {
    @Autowired
    private InterestingFactsService interestingFactsService;

    @RequestMapping(value = "/match/{matchday}",method = RequestMethod.GET)
    public Optional<MatchDto> matchWithTheMostAmountGoals(@PathVariable int matchday){
        return interestingFactsService.matchWithTheMostAmountGoals(matchday);
    }
    @RequestMapping(value = "/team/{teamName}",method = RequestMethod.GET)
    public List<MatchDto> matchesWithTheGivenTeam(@PathVariable String teamName){
        return interestingFactsService.matchesWithTheGivenTeam(teamName);
    }
    @RequestMapping(value = "/winnerTeam/{teamName}",method = RequestMethod.GET)
    public List<MatchDto> winningMatchesWithAGivenTeam(@PathVariable String teamName){
        return interestingFactsService.winningMatchesWithAGivenTeam(teamName);
    }

    @RequestMapping(value = "/bestTeam",method = RequestMethod.GET)
    public Optional<TeamDto> teamWithTheMostVictories(){
        return interestingFactsService.teamWithTheMostVictories();
    }

    @RequestMapping(value = "/victoriesInARow",method = RequestMethod.GET)
    public Optional<TeamDto> teamWithTheMostVictoriesInARow(){
        return interestingFactsService.teamWithTheMostVictoriesInARow();
    }


}
