package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.zagorski.FootballDataRest.dto.MatchWebDto;
import pl.zagorski.FootballDataRest.dto.TeamWebDto;
import pl.zagorski.FootballDataRest.model.match.HomeTeam;
import pl.zagorski.FootballDataRest.service.FootballTeamsFromAPI;
import pl.zagorski.FootballDataRest.service.InterestingFactsService;
import pl.zagorski.FootballDataRest.service.MatchServiceImpl;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/teams")
@Controller
public class FootballTeamsFromAPIController {

    @Autowired
    private InterestingFactsService interestingFactsService;

    @Autowired
    private FootballTeamsFromAPI footballTeamsFromAPI;

    @Autowired
    private MatchServiceImpl matchService;


    @GetMapping("/all")
    public List<String> getAll() {
        return footballTeamsFromAPI.takeToList();
    }

    @GetMapping("/list")
    public String getAll(Model model) {

        model.addAttribute("matchList", footballTeamsFromAPI.takeToList());
        return "/listMatchDto";
    }

    @GetMapping("/allTeams")
    public List<HomeTeam> getAllTeams() {
        return footballTeamsFromAPI.getAll();
    }

    @RequestMapping(value = "/winnerTeam", method = RequestMethod.POST)
    public String winningMatchesWithAGivenTeam(Model model, @ModelAttribute @Valid TeamWebDto team, BindingResult bindingResult) {
        model.addAttribute("matchList", matchService.winningMatchesWithAGivenTeam(team.getName()));
        model.addAttribute("teams", footballTeamsFromAPI.takeToList());
        model.addAttribute("team", team);
        model.addAttribute("matchToAdd", MatchWebDto.builder().build());
        return "/listMatchDto";
    }


    @RequestMapping(value = "/selectTeam", method = RequestMethod.GET)
    public String selectTeam(Model model) {
        model.addAttribute("teams", footballTeamsFromAPI.takeToList());
        model.addAttribute("team", TeamWebDto.builder().build());
        return "/selectTeam";
    }


}
