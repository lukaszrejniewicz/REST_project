package pl.zagorski.FootballDataRest.model.match.score;

public class Score {
    private String winner;
    private String duration;
    private FullTime fullTime;
    private HalfTime halfTime;
    private Penalty penalties;

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public FullTime getFullTime() {
        return fullTime;
    }

    public void setFullTime(FullTime fullTime) {
        this.fullTime = fullTime;
    }

    public HalfTime getHalfTime() {
        return halfTime;
    }

    public void setHalfTime(HalfTime halfTime) {
        this.halfTime = halfTime;
    }

    public Penalty getPenalties() {
        return penalties;
    }

    public void setPenalties(Penalty penalties) {
        this.penalties = penalties;
    }
}
