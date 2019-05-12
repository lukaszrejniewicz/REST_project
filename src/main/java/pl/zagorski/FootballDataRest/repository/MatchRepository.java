package pl.zagorski.FootballDataRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {

}
