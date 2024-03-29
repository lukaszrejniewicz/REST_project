package pl.zagorski.FootballDataRest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
public class TeamWebDto {
    private int id = -1;
    private String name;

    @Tolerate
    public TeamWebDto() {
    }
}
