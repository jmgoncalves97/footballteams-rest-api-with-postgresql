package com.example.footballteams.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FootballTeamMapper {
    
    public static String objectToJson(Object footballTeam) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(footballTeam);
    }
}
