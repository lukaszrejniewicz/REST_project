package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.dto.TeamDto;
import pl.zagorski.FootballDataRest.dto.TeamFormDto;
import pl.zagorski.FootballDataRest.model.Team;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl {
    @Autowired
    private TeamRepository teamRepository;

    public List<TeamFormDto> getAll() {
        List<TeamEntity> teamEntityList = teamRepository.findAll();
        List<TeamFormDto> result = new ArrayList<>();
        for (TeamEntity teamEntity : teamEntityList) {
            TeamFormDto tmp = new TeamFormDto();
            tmp.setName(teamEntity.getName());
            tmp.setId(teamEntity.getId());
            result.add(tmp);
        }
        return result;
    }

    public TeamEntity add(TeamFormDto team) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setName(team.getName());
        return teamRepository.save(teamEntity);
    }
}
