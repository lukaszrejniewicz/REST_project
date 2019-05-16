package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.zagorski.FootballDataRest.dto.MatchDto;
import pl.zagorski.FootballDataRest.model.match.HomeTeam;
import pl.zagorski.FootballDataRest.service.FootballTeamsFromAPI;
import pl.zagorski.FootballDataRest.service.InterestingFactsService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/teams")
@RestController
public class FootballTeamsFromAPIController {

    @Autowired
    private InterestingFactsService interestingFactsService;

    @Autowired
    private FootballTeamsFromAPI footballTeamsFromAPI;

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


    @RequestMapping(value = "/winnerTeam/AS Roma", method = RequestMethod.GET)
    public String winningMatchesWithAGivenTeam(Model model, @ModelAttribute @Valid String teamName, BindingResult bindingResult) {
        teamName = "AS Roma";
        model.addAttribute("matchDto", interestingFactsService.winningMatchesWithAGivenTeam(teamName));
        return "/listMatchDto";
    }

}
