package pl.zagorski.FootballDataRest.model.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import java.util.List;

@Entity(name = "Team")
@Getter @Setter
public class TeamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "homeTeam")
    private List<MatchEntity> homeMatchList;

    @OneToMany(mappedBy = "awayTeam")
    private List<MatchEntity> awayMatchList;
}
