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
import org.springframework.web.bind.annotation.ResponseBody;
import pl.zagorski.FootballDataRest.dto.TeamDto;
import pl.zagorski.FootballDataRest.dto.TeamFormDto;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.service.TeamServiceImpl;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/team")
public class TeamController {
    private final TeamServiceImpl teamService;

    @Autowired
    public TeamController(TeamServiceImpl teamService) {
        this.teamService = teamService;
    }

    @GetMapping(path = "/list")
    public String getAll(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "/teamList";
    }

    @GetMapping(path = "/addForm")
    public String addForm(Model model) {
        model.addAttribute("Team", new TeamFormDto());
        model.addAttribute("teams", teamService.getAll());
        return "/addTeam";
    }

    @PostMapping(path = "/add")
    public String add(Model model, @ModelAttribute @Valid TeamFormDto teamFormDto,
                      BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            teamService.add(teamFormDto);
            model.addAttribute("teams", teamService.getAll());
            return "/teamList";
        } else {
            model.addAttribute("Team", new TeamFormDto());
            return "/addTeam";
        }
    }

    @GetMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        teamService.delete(id);
        model.addAttribute("teams", teamService.getAll());
        return "/teamList";
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        TeamFormDto teamFormDto = new TeamFormDto();
        teamFormDto.setId(id);
        teamFormDto.setName(teamService.findById(id).getName());
        System.out.println(teamFormDto.getId());
        model.addAttribute("Team", teamFormDto);
        return "/addTeam";
    }

}
