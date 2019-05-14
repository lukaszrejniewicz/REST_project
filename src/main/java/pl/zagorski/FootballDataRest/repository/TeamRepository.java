package pl.zagorski.FootballDataRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {

    List<TeamEntity> findByName(String name);

}
