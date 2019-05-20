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

import javax.annotation.Priority;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TeamServiceImplTest {

    @Autowired
    private TeamServiceImpl teamService;

    @Test
    public void findById() {
        TeamWebDto teamWebDto = new TeamWebDto();
        teamWebDto.setId(1);
        teamWebDto.setName("AS Roma");
        teamService.add(teamWebDto);
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamWebDto.getId());
        teamEntity.setName(teamWebDto.getName());
        Assert.assertEquals(teamEntity, teamService.findById(1));
    }

    @Test(expected = NoSuchElementException.class)
    public void findByIdException() {
        TeamWebDto teamWebDto = new TeamWebDto();
        teamWebDto.setId(1);
        teamWebDto.setName("AS Roma");
        teamService.add(teamWebDto);
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamWebDto.getId());
        teamEntity.setName(teamWebDto.getName());
        Assert.assertEquals(teamEntity, teamService.findById(2));
    }

    @Test
    public void findByName() {
        TeamWebDto teamWebDto = new TeamWebDto();
        teamWebDto.setId(1);
        teamWebDto.setName("AS Roma");
        teamService.add(teamWebDto);
        List<TeamEntity> teamEntities = new ArrayList<>();
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamWebDto.getId());
        teamEntity.setName(teamWebDto.getName());

        teamEntities.add(teamEntity);
        Assert.assertEquals(teamEntities, teamService.findByName("AS Roma"));
    }




    @Test
    public void updateTeam() {

        TeamWebDto teamWebDto = new TeamWebDto();
        teamWebDto.setId(1);
        teamWebDto.setName("AS Roma");
        teamService.add(teamWebDto);
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamEntity.getId());
        teamEntity.setName(teamEntity.getName());
        teamService.updateTeam(1,"Juventus");
        assertEquals("Juventus", teamService.findById(1).getName());

    }

    @Test(expected = NoSuchElementException.class)
    public void delete() {
        TeamWebDto teamWebDto = new TeamWebDto();
        teamWebDto.setId(1);
        teamWebDto.setName("AS Roma");
        teamService.add(teamWebDto);

        TeamWebDto teamWebDto2 = new TeamWebDto();
        teamWebDto.setId(2);
        teamWebDto.setName("Juventus");
        teamService.add(teamWebDto2);

        List<TeamEntity> teamEntities = new ArrayList<>();
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setId(teamWebDto.getId());
        teamEntity.setName(teamWebDto.getName());
        teamService.delete(2);
        Assert.assertEquals(teamEntities, teamService.findById(2));

    }
}