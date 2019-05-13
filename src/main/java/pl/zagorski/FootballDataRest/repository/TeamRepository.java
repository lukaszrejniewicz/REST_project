package pl.zagorski.FootballDataRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

    TeamEntity findAllByName(String name);

}
