package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.dto.EntityDtoMapper;
import pl.zagorski.FootballDataRest.dto.TeamWebDto;
import pl.zagorski.FootballDataRest.exception.NotFoundException;
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

    public List<TeamWebDto> getAll() {
        List<TeamEntity> teamEntityList = teamRepository.findAll();
        List<TeamWebDto> result = new ArrayList<>();
        for (TeamEntity teamEntity : teamEntityList) {
            TeamWebDto teamWebDto = EntityDtoMapper.getTeamWebDto(teamEntity);
            result.add(teamWebDto);
        }
        return result;
    }

    public TeamEntity add(TeamWebDto team) {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(team.getId());
        teamEntity.setName(team.getName());
        return teamRepository.save(teamEntity);
    }

    public List<TeamEntity> findByName(String name) {

        return teamRepository.findByName(name);
    }

    public void delete(int id) {
        Optional<TeamEntity> team = teamRepository.findById(id);
        if (team.isPresent()) {
            if (!team.get().getHomeMatchList().isEmpty() || !team.get().getAwayMatchList().isEmpty()) {
                throw new UnsupportedOperationException("you cannot delete a team with matches ;|");
            } else {
                teamRepository.deleteById(id);
            }
        } else {
            throw new NotFoundException("Team not found");
        }

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
