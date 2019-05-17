package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.dto.MatchWebDto;
import pl.zagorski.FootballDataRest.exception.NotFoundException;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.repository.MatchRepository;
import pl.zagorski.FootballDataRest.repository.TeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl  {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    public MatchWebDto findById(int id) {
        MatchEntity matchEntity = matchRepository.findById(id).get();
        MatchWebDto matchDto = new MatchWebDto();
        matchDto.setId(matchEntity.getId());
        matchDto.setHomeTeam(matchEntity.getHomeTeam().getName());
        matchDto.setAwayTeam(matchEntity.getAwayTeam().getName());
        return matchDto;
    }

    public MatchEntity save(MatchWebDto form) {
        MatchEntity entity = new MatchEntity();
        entity.setId(form.getId());
        entity.setHomeTeam(teamRepository.findById(form.getHomeTeamId()).get());
        entity.setAwayTeam(teamRepository.findById(form.getAwayTeamId()).get());
        return matchRepository.save(entity);
    }

    public List<MatchWebDto> getAll() {
        List<MatchWebDto> result = new ArrayList<>();
        for (MatchEntity matchEntity : matchRepository.findAll()) {
            MatchWebDto tmp = new MatchWebDto();
            tmp.setId(matchEntity.getId());
            tmp.setHomeTeam(matchEntity.getHomeTeam().getName());
            tmp.setAwayTeam(matchEntity.getAwayTeam().getName());
            result.add(tmp);
        }
        return result;
    }

    public void delete(int id) {
        matchRepository.deleteById(id);
    }

    public MatchWebDto updateMatch(int id, String homeTeam, String awayTeam) {
        Optional<MatchEntity> matchExist = matchRepository.findById(id);
        if(!matchExist.isPresent()) {
            throw new NotFoundException("Match not found");
        }

        TeamEntity homeTeamExist = teamRepository.findByName(homeTeam).get(0);
        Optional<TeamEntity> team1 = teamRepository.findById(homeTeamExist.getId());
        if(!team1.isPresent()){
            throw new NotFoundException("homeTeam not found");
        }

        TeamEntity awayTeamExist = teamRepository.findByName(awayTeam).get(0);
        Optional<TeamEntity> team2 = teamRepository.findById(awayTeamExist.getId());
        if(!team2.isPresent()){
            throw new NotFoundException("awayTeam not found");
        }

        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(id);
        matchEntity.setHomeTeam(homeTeamExist);
        matchEntity.setAwayTeam(awayTeamExist);
        matchRepository.save(matchEntity);

        MatchWebDto matchWebDto = new MatchWebDto();
        matchWebDto.setId(id);
        matchWebDto.setHomeTeam(homeTeam);
        matchWebDto.setAwayTeam(awayTeam);

        return matchWebDto;
    }

}
