package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zagorski.FootballDataRest.dto.MatchListDto;
import pl.zagorski.FootballDataRest.exception.NotFoundException;
import pl.zagorski.FootballDataRest.exception.TeamException;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.service.MatchServiceImpl;
import pl.zagorski.FootballDataRest.service.TeamServiceImpl;

@RestController
@RequestMapping(path = "/restMatch")
public class MatchRestController {

    @Autowired
    private MatchServiceImpl matchService;

    @Autowired
    private TeamServiceImpl teamService;


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        matchService.delete(id);
    }

    @GetMapping("/updateMatch/{id}/{homeTeam}/{awayTeam}")
    public MatchListDto updateMatch(@PathVariable int id, @PathVariable String homeTeam, @PathVariable String awayTeam) throws NotFoundException, NullPointerException {
        return matchService.updateMatch(id, homeTeam, awayTeam);
    }
}
