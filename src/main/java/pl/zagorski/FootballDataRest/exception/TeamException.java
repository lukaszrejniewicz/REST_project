package pl.zagorski.FootballDataRest.exception;

public class TeamException extends NullPointerException {

    public TeamException() {
        super("Not found");
    }
}
