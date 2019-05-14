package pl.zagorski.FootballDataRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.zagorski.FootballDataRest.service.FootballTeamsFromAPI;

import java.util.List;

@RequestMapping("/teams")
@RestController
public class FootballTeamsFromAPIController {

    @Autowired
    FootballTeamsFromAPI footballTeamsFromAPI;

    @GetMapping("/all")
    public List<String> getAll() {
        return footballTeamsFromAPI.takeToList();
    }
}
