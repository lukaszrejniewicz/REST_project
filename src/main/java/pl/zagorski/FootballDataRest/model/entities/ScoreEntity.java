package pl.zagorski.FootballDataRest.model.entities;

import lombok.Getter;
import lombok.Setter;
import pl.zagorski.FootballDataRest.model.match.score.FullTime;
import pl.zagorski.FootballDataRest.model.match.score.HalfTime;
import pl.zagorski.FootballDataRest.model.match.score.Penalty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "Score")
@Getter @Setter
public class ScoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String duration;
    private int homeTeamScore;
    private int awayTeamScore;

//    @OneToOne
////    @JoinColumn(name = "match_id", referencedColumnName = "id")
//    private MatchEntity match;
}
