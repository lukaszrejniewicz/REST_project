package pl.zagorski.FootballDataRest.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.zagorski.FootballDataRest.TestContext;
import pl.zagorski.FootballDataRest.dto.TeamFormDto;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamServiceImplTest {

    TeamFormDto teamFormDto = new TeamFormDto();
    List<TeamEntity> teamEntities = new ArrayList<>();
    @Before
    public void setUp() {

        teamFormDto.setId(1);
        teamFormDto.setName("AS Roma");
        teamService.add(teamFormDto);
    }

    @Autowired
    private TeamServiceImpl teamService;

    @Test
    public void findById() {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamFormDto.getId());
        teamEntity.setName(teamFormDto.getName());
        Assert.assertEquals(teamEntity, teamService.findById(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void findByIdException() {

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamFormDto.getId());
        teamEntity.setName(teamFormDto.getName());
        Assert.assertEquals(teamEntity, teamService.findById(2));
    }

    @Test
    public void findByName() {
        List<TeamEntity> teamEntities = new ArrayList<>();
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamFormDto.getId());
        teamEntity.setName(teamFormDto.getName());

        teamEntities.add(teamEntity);
        Assert.assertEquals(teamEntities, teamService.findByName("AS Roma"));
    }

//    @Test(expected = NoSuchElementException.class)
//    public void zdelete() {
////        TeamFormDto teamFormDto = new TeamFormDto();
////        teamFormDto.setId(1);
////        teamFormDto.setName("AS Roma");
////        teamService.add(teamFormDto);
//
////        List<TeamEntity> teamEntities = new ArrayList<>();
////        TeamEntity teamEntity = new TeamEntity();
////        teamEntity.setId(teamFormDto.getId());
////        teamEntity.setName(teamFormDto.getName());
//        teamService.delete(1);
//        Assert.assertEquals(teamEntities, teamService.findById(1));
//
//    }

    @Test
    public void updateTeam() {

        TeamFormDto teamFormDt2 = new TeamFormDto();
        teamFormDto.setId(1);
        teamFormDto.setName("Juventus");

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamFormDt2.getId());
        teamEntity.setName(teamFormDt2.getName());
        teamService.updateTeam(1,"Juventus");
        assertEquals("Juventus", teamService.findById(1).getName());

    }
}