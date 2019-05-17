package pl.zagorski.FootballDataRest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Builder
public class TeamWebDto {
    private int id;
    private String name;

    @Tolerate
    public TeamWebDto() {
    }

    public static TeamWebDto empty() {
        TeamWebDto result = TeamWebDto.builder()
                .id(0)
                .name("")
                .build();
        return result;
    }
}
