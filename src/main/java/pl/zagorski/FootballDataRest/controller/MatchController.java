package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zagorski.FootballDataRest.dto.MatchWebDto;
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
        model.addAttribute("Match", MatchWebDto.builder().build());
        model.addAttribute("teams", teamService.getAll());
        return "/addMatch";
    }

    @PostMapping("/add")
    public String addForm(Model model, @ModelAttribute @Valid MatchWebDto matchWebDto,
                          BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            System.out.println("you haven't failed succesfully");
            matchService.save(matchWebDto);
            model.addAttribute("matchList", matchService.getAll());
            return "/matchList";
        } else {
            model.addAttribute("Match", MatchWebDto.builder().build());
            model.addAttribute("teams", teamService.getAll());
            return "/addMatch";
        }
    }

    @GetMapping(path = "/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        matchService.delete(id);
        model.addAttribute("matchList", matchService.getAll());
        return "/matchList";
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("Match", matchService.findById(id));
        model.addAttribute("teams", teamService.getAll());
        return "/addMatch";
    }
}
