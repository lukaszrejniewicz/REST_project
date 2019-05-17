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
import pl.zagorski.FootballDataRest.dto.TeamWebDto;
import pl.zagorski.FootballDataRest.model.entities.TeamEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamServiceImplTest {

    TeamWebDto teamWebDto = new TeamWebDto();
    List<TeamEntity> teamEntities = new ArrayList<>();
    @Before
    public void setUp() {

        teamWebDto.setId(1);
        teamWebDto.setName("AS Roma");
        teamService.add(teamWebDto);
    }

    @Autowired
    private TeamServiceImpl teamService;

    @Test
    public void findById() {
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamWebDto.getId());
        teamEntity.setName(teamWebDto.getName());
        Assert.assertEquals(teamEntity, teamService.findById(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void findByIdException() {

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamWebDto.getId());
        teamEntity.setName(teamWebDto.getName());
        Assert.assertEquals(teamEntity, teamService.findById(2));
    }

    @Test
    public void findByName() {
        List<TeamEntity> teamEntities = new ArrayList<>();
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamWebDto.getId());
        teamEntity.setName(teamWebDto.getName());

        teamEntities.add(teamEntity);
        Assert.assertEquals(teamEntities, teamService.findByName("AS Roma"));
    }

//    @Test(expected = NoSuchElementException.class)
//    public void zdelete() {
////        TeamWebDto teamWebDto = new TeamWebDto();
////        teamWebDto.setId(1);
////        teamWebDto.setName("AS Roma");
////        teamService.add(teamWebDto);
//
////        List<TeamEntity> teamEntities = new ArrayList<>();
////        TeamEntity teamEntity = new TeamEntity();
////        teamEntity.setId(teamWebDto.getId());
////        teamEntity.setName(teamWebDto.getName());
//        teamService.delete(1);
//        Assert.assertEquals(teamEntities, teamService.findById(1));
//
//    }

    @Test
    public void updateTeam() {

        TeamWebDto teamFormDt2 = new TeamWebDto();
        teamWebDto.setId(1);
        teamWebDto.setName("Juventus");

        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamFormDt2.getId());
        teamEntity.setName(teamFormDt2.getName());
        teamService.updateTeam(1,"Juventus");
        assertEquals("Juventus", teamService.findById(1).getName());

    }
}