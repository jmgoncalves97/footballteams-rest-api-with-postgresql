package com.example.footballteams.controller;

import java.util.List;

import com.example.footballteams.service.FootballTeamService;
import com.example.footballteams.exception.ResourceNotFoundException;
import com.example.footballteams.model.FootballTeamEntity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class FootballTeamController {

    private final FootballTeamService footballTeamService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<FootballTeamEntity> getAll() {
        return footballTeamService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public FootballTeamEntity getById(@PathVariable int id) throws ResourceNotFoundException {
        return footballTeamService.getById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public FootballTeamEntity create(@RequestBody FootballTeamEntity footballTeam) {
        return footballTeamService.create(footballTeam);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public FootballTeamEntity update(@PathVariable("id") int id, @RequestBody FootballTeamEntity footballTeam)
            throws ResourceNotFoundException {
        return footballTeamService.update(footballTeam, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) throws ResourceNotFoundException {
        footballTeamService.delete(id);
    }
}