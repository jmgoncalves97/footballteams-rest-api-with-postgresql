package com.example.footballteams.controller;

import static com.example.footballteams.builder.FootballTeamBuilder.footballTeamBuilder;
import static com.example.footballteams.mapper.FootballTeamMapper.objectToJson;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.example.footballteams.exception.ResourceNotFoundException;
import com.example.footballteams.model.FootballTeamEntity;
import com.example.footballteams.service.FootballTeamService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = FootballTeamController.class)
public class WebMockTest {

    private static final int ID = 1;

    private static final String PATH_ID_1 = ("/api/v1/teams/" + ID);

    private static final String PATH = "/api/v1/teams";

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private FootballTeamService footballTeamService;

    @Test
    public void whenGetFootballTeamsThenReturnOk() throws Exception{

        FootballTeamEntity footballTeam = footballTeamBuilder();

        List<FootballTeamEntity> footballTeamList = List.of(footballTeam);

        String footballTeamJsonList = objectToJson(footballTeamList);

        Mockito.when(footballTeamService.getAll()).thenReturn(footballTeamList);

        mockMvc.perform(MockMvcRequestBuilders.get(PATH))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.content().json(footballTeamJsonList));
    }

    @Test
    public void whenGetFootballTeamByIdThenReturnOk() throws Exception{

        FootballTeamEntity footballTeam = footballTeamBuilder();

        String footballTeamJson = objectToJson(footballTeam);

        when(footballTeamService.getById(ID)).thenReturn(footballTeam);

        mockMvc.perform(get(PATH_ID_1))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON))
            .andExpect(content().json(footballTeamJson));
    }

    @Test
    public void whenGetFootballTeamThatDoesntExistThrowsExceptionBadRequest() throws Exception{

        when(footballTeamService.getById(ID)).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(PATH_ID_1))
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPostFootballTeamThenReturnCreated() throws Exception{

        FootballTeamEntity footballTeam = footballTeamBuilder();

        String footballTeamJson = objectToJson(footballTeam);

        Mockito.any();

        when(footballTeamService.create(footballTeam)).thenReturn(footballTeam);

        mockMvc.perform(post(PATH)
                .contentType(APPLICATION_JSON)
                .content(footballTeamJson)
                .accept(APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().json(footballTeamJson));
    }

    @Test
    public void whenDeleteFootballTeamThenReturnNoContent() throws Exception {

        Mockito.doNothing().when(footballTeamService).delete(ID);

        mockMvc.perform(delete(PATH_ID_1))
            .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeleteFootballTeamDoesntExistThenReturnNoContent() throws Exception {

        Mockito.doThrow(ResourceNotFoundException.class).when(footballTeamService).delete(ID);

        mockMvc.perform(delete(PATH_ID_1))
            .andExpect(status().isBadRequest());
    }
}
