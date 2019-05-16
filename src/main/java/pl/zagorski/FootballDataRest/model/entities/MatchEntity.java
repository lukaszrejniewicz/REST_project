package pl.zagorski.FootballDataRest.model.entities;

import lombok.Getter;
import lombok.Setter;
import pl.zagorski.FootballDataRest.dto.ScoreDto;
import pl.zagorski.FootballDataRest.model.match.score.Score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity(name = "Match")
@Getter @Setter
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int matchday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "homeTeam_id")
    private TeamEntity homeTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "awayTeam_id")
    private TeamEntity awayTeam;

    @Column(name = "groupName")
    private String group;

    @OneToOne
//    @JoinColumn(name = "score_id", referencedColumnName = "id")
    private ScoreEntity score;
}
