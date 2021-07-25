package com.example.footballteams.service;

import static org.mockito.Mockito.when;

import java.util.Optional;

import com.example.footballteams.builder.FootballTeamBuilder;
import com.example.footballteams.exception.ResourceNotFoundException;
import com.example.footballteams.model.FootballTeamEntity;
import com.example.footballteams.repository.FootballTeamRepository;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FootballTeamServiceTest {
    
    private static final Integer ID = 1;

    @Mock
    private FootballTeamRepository footballTeamRepository;

    @InjectMocks
    private FootballTeamService footballTeamService;

    @Test
    public void whenPutFootballTeamThenReturnFootballTeam() throws ResourceNotFoundException {

        FootballTeamEntity footballTeam = FootballTeamBuilder.footballTeamBuilder();

        Mockito.when(footballTeamRepository.findById(ID)).thenReturn(Optional.of(footballTeam));
        Mockito.when(footballTeamRepository.save(footballTeam)).thenReturn(footballTeam);

        MatcherAssert.assertThat(footballTeamService.update(footballTeam, ID), Matchers.is(Matchers.equalTo(footballTeam)));
    }

    @Test
    public void whenPutFootballTeamThatIdDoesntExistThenThrowsException() {

        FootballTeamEntity footballTeam = FootballTeamBuilder.footballTeamBuilder();

        when(footballTeamRepository.findById(ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> footballTeamService.update(footballTeam, ID));
    }
}
