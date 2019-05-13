package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.zagorski.FootballDataRest.dto.MatchFormDto;
import pl.zagorski.FootballDataRest.dto.MatchListDto;
import pl.zagorski.FootballDataRest.dto.TeamFormDto;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.service.MatchServiceImpl;
import pl.zagorski.FootballDataRest.service.TeamServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/match")
public class MatchController {
    @Autowired
    private MatchServiceImpl matchService;

    @Autowired
    private TeamServiceImpl teamService;

    @GetMapping("/list")
    public String getAll(Model model) {
        model.addAttribute("matchList", matchService.getAll());
        return "/matchList";
    }

    @GetMapping("/addForm")
    public String addForm(Model model) {
        model.addAttribute("Match", new MatchFormDto());
        model.addAttribute("teams", teamService.getAll());
        return "/addMatch";
    }

    @PostMapping("/add")
    public String addForm(Model model, @ModelAttribute @Valid MatchFormDto matchFormDto,
                          BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            matchService.save(matchFormDto);
            model.addAttribute("matchList", matchService.getAll());
            return "/matchList";
        } else {
            model.addAttribute("Match", new MatchFormDto());
            return "/addMatch";
        }
    }


}
