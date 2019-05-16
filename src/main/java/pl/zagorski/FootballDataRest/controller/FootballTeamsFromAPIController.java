package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.zagorski.FootballDataRest.dto.MatchDto;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.model.match.HomeTeam;
import pl.zagorski.FootballDataRest.service.FootballTeamsFromAPI;
import pl.zagorski.FootballDataRest.service.InterestingFactsService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/teams")
@Controller
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


    @RequestMapping(value = "/winnerTeam/{teamName}", method = RequestMethod.GET)
    public String winningMatchesWithAGivenTeam(Model model, @PathVariable String teamName, BindingResult bindingResult) {
        //teamName = "AS Roma";
        System.out.println("XDXDXD");
        System.out.println(teamName);
        model.addAttribute("matchDto", interestingFactsService.winningMatchesWithAGivenTeam(teamName));
        model.addAttribute("teams", footballTeamsFromAPI.takeToList());
        model.addAttribute("teamName", new String());
        return "/listMatchDto";
    }

    @RequestMapping(value = "/selectTeam", method = RequestMethod.GET)
    public String selectTeam(Model model) {
        model.addAttribute("teams", footballTeamsFromAPI.takeToList());
        model.addAttribute("teamName", new String());
        return "/selectTeam";
    }



}
