package pl.zagorski.FootballDataRest.dto;

public class MatchDto {
    private int matchday;
    private String winnerTeam;
    private int goals;
    private String homeTeam;
    private String awayTeam;

    public static class MatchDtoBuilder {
        private int matchday;
        private String winnerTeam;
        private int goals;
        private String homeTeam;
        private String awayTeam;

        public MatchDtoBuilder matchday(int matchday) {
            this.matchday = matchday;
            return this;
        }

        public MatchDtoBuilder winnerTeam(String winnerTeam) {
            this.winnerTeam = winnerTeam;
            return this;
        }

        public MatchDtoBuilder homeTeam(String homeTeam) {
            this.homeTeam = homeTeam;
            return this;
        }

        public MatchDtoBuilder awayTeam(String awayTeam) {
            this.awayTeam = awayTeam;
            return this;
        }

        public MatchDtoBuilder goals(int goals) {
            this.goals = goals;
            return this;
        }

        public MatchDto build() {
            MatchDto matchDto = new MatchDto();
            matchDto.matchday = this.matchday;
            matchDto.winnerTeam = this.winnerTeam;
            matchDto.homeTeam = this.homeTeam;
            matchDto.awayTeam = this.awayTeam;
            matchDto.goals = this.goals;
            return matchDto;
        }
    }

    public int getMatchday() {
        return matchday;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public int getGoals() {
        return goals;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }
}
