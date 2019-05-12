package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.dto.MatchFormDto;
import pl.zagorski.FootballDataRest.dto.MatchListDto;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.repository.MatchRepository;
import pl.zagorski.FootballDataRest.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchServiceImpl {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    public MatchEntity save(MatchFormDto form) {
        MatchEntity entity = new MatchEntity();
        entity.setHomeTeam(teamRepository.findById(form.getHomeTeamId()).get());
        entity.setAwayTeam(teamRepository.findById(form.getAwayTeamId()).get());
        return matchRepository.save(entity);
    }

    public List<MatchListDto> getAll() {
        List<MatchListDto> result = new ArrayList<>();
        for (MatchEntity matchEntity : matchRepository.findAll()) {
            MatchListDto tmp = new MatchListDto();
            tmp.setId(matchEntity.getId());
            tmp.setHomeTeam(matchEntity.getHomeTeam().getName());
            tmp.setAwayTeam(matchEntity.getAwayTeam().getName());
            result.add(tmp);
        }
        return result;
    }
}
