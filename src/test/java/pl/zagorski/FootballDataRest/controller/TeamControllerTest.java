package pl.zagorski.FootballDataRest.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import pl.zagorski.FootballDataRest.TestContext;

import static org.junit.Assert.*;
@WebAppConfiguration
@SpringBootTest(classes = TestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class TeamControllerTest {

    @Autowired
    private TeamController teamController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/team/list"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void addForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/team/addForm"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}