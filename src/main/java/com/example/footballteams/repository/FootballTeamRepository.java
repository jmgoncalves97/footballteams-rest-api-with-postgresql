package com.example.footballteams.repository;

import com.example.footballteams.model.FootballTeamEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootballTeamRepository extends JpaRepository<FootballTeamEntity, Integer> {}
