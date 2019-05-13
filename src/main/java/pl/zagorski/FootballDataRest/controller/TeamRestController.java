package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.service.TeamServiceImpl;

@RestController
@RequestMapping(path = "/restTeam")
public class TeamRestController {

    private final TeamServiceImpl teamService;

    public TeamRestController(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    @GetMapping(path = "/findByName/{name}")
    public TeamEntity findByName(@PathVariable String name) {
        return teamService.findByName(name);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        teamService.delete(id);
    }

    @GetMapping("/updateTeam/{id}/{team}")
    public TeamEntity updateMatch(@PathVariable int id, @PathVariable String team) {
        return teamService.updateTeam(id, team);
    }
}
