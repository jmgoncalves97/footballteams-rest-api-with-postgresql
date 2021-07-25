package com.example.footballteams.service;

import java.util.List;

import com.example.footballteams.exception.ResourceNotFoundException;
import com.example.footballteams.model.FootballTeamEntity;
import com.example.footballteams.repository.FootballTeamRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FootballTeamService {

    private final FootballTeamRepository footballTeamRepository;

    public FootballTeamEntity create(FootballTeamEntity footballTeam) {
        return footballTeamRepository.save(footballTeam);
    }

    public FootballTeamEntity getById(int id) throws ResourceNotFoundException {

        footballTeamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Time com id %d não encontrado", id));

        return footballTeamRepository.findById(id).get();
    }

    public List<FootballTeamEntity> getAll() {

        return footballTeamRepository.findAll();
    }

    public FootballTeamEntity update(FootballTeamEntity ft, int id) throws ResourceNotFoundException {

        FootballTeamEntity footballTeam = footballTeamRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Time com id %d não encontrado", id));
            
        footballTeam.setTeam(ft.getTeam());
        footballTeam.setCountry(ft.getCountry());

        return footballTeamRepository.save(footballTeam);
    }

    public void delete(int id) throws ResourceNotFoundException {

        FootballTeamEntity footballTeam = footballTeamRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Time com id %d não encontrado", id));
            
        footballTeamRepository.delete(footballTeam);
    }
}
