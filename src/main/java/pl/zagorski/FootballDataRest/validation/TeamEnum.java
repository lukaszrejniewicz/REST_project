package pl.zagorski.FootballDataRest.validation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import pl.zagorski.FootballDataRest.exception.NotFoundException;

public enum TeamEnum {

    CHIEVO("AC Chievo Verona"),
    MILAN("AC Milan"),
    FIORENTINA("ACF Fiorentina"),
    ROMA("AS Roma"),
    ATALANTA("Atalanta BC"),
    BOLOGNA("Bologna FC 1909"),
    CAGLIARI("Cagliari Calcio"),
    EMPOLO("Empoli FC"),
    INTER("FC Internazionale Milano"),
    FROSINONE("Frosinone Calcio"),
    GENOA("Genoa CFC"),
    JUVE("Juventus FC"),
    PARMA("Parma Calcio 1913"),
    SPAL("SPAL 2013"),
    LAZIO("SS Lazio"),
    NAPOLI("SSC Napoli"),
    TORINO("Torino FC"),
    SAMPDORIA("UC Sampdoria"),
    SASSUOLO("US Sassuolo Calcio"),
    UDINESE("Udinese Calcio");

    private String name;

    TeamEnum(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static TeamEnum checkTeam(String name) {
        TeamEnum result = null;
        for (TeamEnum teamEnum : TeamEnum.values()) {
            if (teamEnum.getName().equals(name)) {
                result = teamEnum;
                break;
            }
        }
        if(result == null){
            throw new NotFoundException("Nie znaleziono klubu o nazwie: "+name);
        }
        return result;
    }
}
