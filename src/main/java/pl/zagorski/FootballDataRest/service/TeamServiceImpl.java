package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.dto.TeamDto;
import pl.zagorski.FootballDataRest.dto.TeamFormDto;
import pl.zagorski.FootballDataRest.exception.NotFoundException;
import pl.zagorski.FootballDataRest.model.Team;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl {
    @Autowired
    private TeamRepository teamRepository;

    public TeamEntity findById(int id) {
        return teamRepository.findById(id).get();
    }

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
        teamEntity.setId(team.getId());
        teamEntity.setName(team.getName());
        return teamRepository.save(teamEntity);
    }

    public List<TeamEntity> findByName(String name) {

        return teamRepository.findByName(name);
    }

    public void delete(int id) {
        teamRepository.deleteById(id);
    }

    public TeamEntity updateTeam(int id, String team) {
        Optional<TeamEntity> teamExist = teamRepository.findById(id);
        if(!teamExist.isPresent()) {
            throw new NotFoundException("Team not found");
        }

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(id);
        teamEntity.setName(team);
        teamRepository.save(teamEntity);

        return teamEntity;
    }

}
