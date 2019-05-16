package pl.zagorski.FootballDataRest.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.zagorski.FootballDataRest.TestContext;
import pl.zagorski.FootballDataRest.dto.MatchFormDto;
import pl.zagorski.FootballDataRest.model.entities.MatchEntity;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;

import static org.junit.Assert.*;
@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MatchServiceImplTest {

    @Autowired
    private MatchServiceImpl matchService;

    MatchFormDto matchFormDto = new MatchFormDto();
    TeamEntity teamEntity = new TeamEntity();
    @Before
    public void setUp() {


        matchFormDto.setId(1);
        matchFormDto.setHomeTeam("AS Roma");
        matchFormDto.setAwayTeam("Juventus");


    }

    @Test
    public void findById() {
        MatchFormDto matchFormDto = new MatchFormDto();
        matchFormDto.setId(1);
        matchFormDto.setHomeTeam("AS Roma");
        matchFormDto.setAwayTeam("Juventus");

        matchService.save(matchFormDto);

        Assert.assertEquals(matchFormDto, matchService.findById(1));

    }

    @Test
    public void getAll() {

    }

    @Test
    public void delete() {
    }

    @Test
    public void updateMatch() {

        MatchFormDto matchFormDto = new MatchFormDto();
        matchFormDto.setId(1);
        matchFormDto.setHomeTeam("AS Roma");
        matchFormDto.setAwayTeam("Juventus");

        matchService.save(matchFormDto);
        MatchFormDto matchFormDto2 = new MatchFormDto();
        matchFormDto2.setId(1);
        matchFormDto2.setHomeTeam("Juventus");
        matchFormDto2.setAwayTeam("AS Roma");
        matchService.updateMatch(1, "Juventus", "AS Roma");

        assertEquals("Juventus", matchService.findById(1).getHomeTeam());
    }
}