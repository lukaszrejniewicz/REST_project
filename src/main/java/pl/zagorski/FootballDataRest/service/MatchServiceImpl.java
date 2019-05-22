package pl.zagorski.FootballDataRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.zagorski.FootballDataRest.dto.EntityDtoMapper;
import pl.zagorski.FootballDataRest.dto.MatchWebDto;
import pl.zagorski.FootballDataRest.dto.ModelDtoMapper;
import pl.zagorski.FootballDataRest.exception.NotFoundException;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;
import pl.zagorski.FootballDataRest.model.match.Match;
import pl.zagorski.FootballDataRest.repository.MatchRepository;
import pl.zagorski.FootballDataRest.repository.TeamRepository;
import pl.zagorski.FootballDataRest.validation.TeamEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MatchServiceImpl {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private FootballDataService footballDataService;

    public MatchWebDto findById(int id) {
        Optional<MatchEntity> matchEntity = matchRepository.findById(id);
        if (!matchEntity.isPresent()) {
            throw new NotFoundException("Match not found");
        }
        MatchWebDto matchDto = EntityDtoMapper.getMatchWebDto(matchEntity.get());
        return matchDto;
    }

    public MatchEntity save(MatchWebDto matchWebDto) {
        if (matchWebDto.getHomeTeam().getName().equals(matchWebDto.getAwayTeam().getName())) {
            throw new IllegalArgumentException("homeTeam and awayTeam must be different");
        }

        if (matchWebDto.getHomeTeamGoals() < 0 || matchWebDto.getAwayTeamGoals() < 0) {
            throw new IllegalArgumentException("goals number must be nonnegative");
        }

        if (matchWebDto.getMatchday() < 1) {
            throw new IllegalArgumentException("matchday number must be positive");
        }

        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(matchWebDto.getId());

        if (teamRepository.findByName(matchWebDto.getHomeTeam().getName()).isEmpty()) {
            TeamEntity homeTeamEntity = new TeamEntity();
            homeTeamEntity.setName(matchWebDto.getHomeTeam().getName());
            teamRepository.save(homeTeamEntity);
            matchEntity.setHomeTeam(homeTeamEntity);
        } else if (teamRepository.findById(matchWebDto.getHomeTeam().getId()).isPresent()) {
            matchEntity.setHomeTeam(teamRepository.findById(matchWebDto.getHomeTeam().getId()).get());
        } else {
            matchEntity.setHomeTeam(teamRepository.findByName(matchWebDto.getHomeTeam().getName()).get(0));
        }
        if (teamRepository.findByName(matchWebDto.getAwayTeam().getName()).isEmpty()) {
            TeamEntity awayTeamEntity = new TeamEntity();
            awayTeamEntity.setName(matchWebDto.getAwayTeam().getName());
            teamRepository.save(awayTeamEntity);
            matchEntity.setAwayTeam(awayTeamEntity);
        } else if (teamRepository.findById(matchWebDto.getAwayTeam().getId()).isPresent()) {
            matchEntity.setAwayTeam(teamRepository.findById(matchWebDto.getAwayTeam().getId()).get());
        } else {
            matchEntity.setAwayTeam(teamRepository.findByName(matchWebDto.getAwayTeam().getName()).get(0));
        }

        matchEntity.setHomeTeamGoals(matchWebDto.getHomeTeamGoals());
        matchEntity.setAwayTeamGoals(matchWebDto.getAwayTeamGoals());

        matchEntity.setDuration(matchWebDto.getDuration());
        matchEntity.setMatchday(matchWebDto.getMatchday());
        matchEntity.setGroup(matchWebDto.getGroup());

        return matchRepository.save(matchEntity);
    }

    public List<MatchWebDto> getAllPagination(int pageNumber, int pageSize) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<MatchWebDto> result = new ArrayList<>();
        for (MatchEntity matchEntity : matchRepository.findAll(page)) {
            MatchWebDto matchWebDto = EntityDtoMapper.getMatchWebDto(matchEntity);
            result.add(matchWebDto);
        }
        return result;
    }

    public List<MatchWebDto> getAll() {
        List<MatchWebDto> result = new ArrayList<>();
        for (MatchEntity matchEntity : matchRepository.findAll()) {
            MatchWebDto matchWebDto = EntityDtoMapper.getMatchWebDto(matchEntity);
            result.add(matchWebDto);
        }
        return result;
    }

    public void delete(int id) {
        if (!matchRepository.findById(id).isPresent()) {
            throw new NotFoundException("Match not found");
        } else {
            matchRepository.deleteById(id);
        }
    }

    public MatchWebDto updateMatch(int id, String homeTeam, String awayTeam) {
        Optional<MatchEntity> matchExist = matchRepository.findById(id);
        if (!matchExist.isPresent()) {
            throw new NotFoundException("Match not found");
        }

        TeamEntity homeTeamExist = teamRepository.findByName(homeTeam).get(0);
        Optional<TeamEntity> team1 = teamRepository.findById(homeTeamExist.getId());
        if (!team1.isPresent()) {
            throw new NotFoundException("homeTeam not found");
        }

        TeamEntity awayTeamExist = teamRepository.findByName(awayTeam).get(0);
        Optional<TeamEntity> team2 = teamRepository.findById(awayTeamExist.getId());
        if (!team2.isPresent()) {
            throw new NotFoundException("awayTeam not found");
        }

        MatchEntity matchEntity = new MatchEntity();
        matchEntity.setId(id);
        matchEntity.setHomeTeam(homeTeamExist);
        matchEntity.setAwayTeam(awayTeamExist);
        matchRepository.save(matchEntity);

        MatchWebDto matchWebDto = new MatchWebDto();
        matchWebDto.setId(id);
        matchWebDto.setHomeTeam(EntityDtoMapper.getTeamWebDto(teamRepository.findByName(homeTeam).get(0)));
        matchWebDto.setAwayTeam(EntityDtoMapper.getTeamWebDto(teamRepository.findByName(awayTeam).get(0)));

        return matchWebDto;
    }

    public List<MatchWebDto> matchesWithTheGivenTeam(String name) {
        TeamEnum.checkTeam(name);
        List<Match> matchList = footballDataService.getAllData().getMatches();
        List<MatchWebDto> result = matchList
                .stream()
                .filter(match -> match.getHomeTeam().getName().equals(name) || match.getAwayTeam().getName().equals(name))
                .filter(match -> match.getScore().getWinner() != null)
                .map(ModelDtoMapper::getMatchWebDto)
                .collect(Collectors.toList());
        return result;
    }

    public List<MatchWebDto> winningMatchesWithAGivenTeam(String name) {
        TeamEnum.checkTeam(name);

        List<MatchWebDto> result = new ArrayList<>();
        for (MatchWebDto match : matchesWithTheGivenTeam(name)) {
            if (match.getHomeTeam().getName().equals(name) && match.getHomeTeamGoals() > match.getAwayTeamGoals()) {
                result.add(match);
            } else if (match.getAwayTeam().getName().equals(name) && match.getAwayTeamGoals() > match.getHomeTeamGoals()) {
                result.add(match);
            }
        }
        return result;
    }

}
