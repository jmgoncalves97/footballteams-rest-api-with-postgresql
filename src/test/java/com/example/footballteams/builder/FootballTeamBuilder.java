package com.example.footballteams.builder;

import com.example.footballteams.model.FootballTeamEntity;

public class FootballTeamBuilder {
    
    public static FootballTeamEntity footballTeamBuilder() {
        return FootballTeamEntity.builder()
            .id(1)
            .team("Name")
            .build();
    }
}
