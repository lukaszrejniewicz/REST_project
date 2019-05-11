package pl.zagorski.FootballDataRest.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;
import pl.zagorski.FootballDataRest.TestContext;

@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class InterestingFactsControllerTest {

    @Autowired
    private InterestingFactsController interestingFactsController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(interestingFactsController).build();
    }

    @Test
    public void matchWithTheMostAmountGoals() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/interestingFacts/match/22"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.matchday").value(22))
                .andExpect(MockMvcResultMatchers.jsonPath("$.winnerTeam").value("WYNIK REMISOWY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.goals").value(6))
                .andExpect(MockMvcResultMatchers.jsonPath("$.homeTeam").value("Juventus FC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.awayTeam").value("Parma Calcio 1913"));
    }

    @Test(expected = NestedServletException.class)
    public void matchesWithTheGivenTeamIncorrectNumber() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/interestingFacts/match/39"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    public void matchesWithTheGivenTeam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/interestingFacts/team/AS Roma"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].matchday").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].winnerTeam").value("AS Roma"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].goals").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].homeTeam").value("Torino FC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].awayTeam").value("AS Roma"));
    }

    @Test(expected = NestedServletException.class)
    public void matchesWithTheGivenIncorrectTeam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/interestingFacts/team/AS RomaX"));
    }

    @Test
    public void winningMatchesWithAGivenTeam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/interestingFacts/winnerTeam/AS Roma"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[14].matchday").value(32))
                .andExpect(MockMvcResultMatchers.jsonPath("$[14].winnerTeam").value("AS Roma"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[14].goals").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[14].homeTeam").value("AS Roma"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[14].awayTeam").value("Udinese Calcio"));
    }

    @Test
    public void teamWithTheMostVictories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/interestingFacts/bestTeam"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Juventus FC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfWins").value(28));//stan na 26/04/2019
    }

    @Test
    public void teamWithTheMostVictoriesInARow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/interestingFacts/victoriesInARow"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Juventus FC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.numberOfWins").value(8));//stan na 26/04/2019
    }
}