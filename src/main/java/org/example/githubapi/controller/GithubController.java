package org.example.githubapi.controller;

import org.example.githubapi.model.Repo;
import org.example.githubapi.service.GithubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
public class GithubController {
    private final GithubService github_service;

    public GithubController(GithubService service){
        this.github_service = service;
    }

@GetMapping("/repos/{user}")
public ResponseEntity<?> getUserRepos(@PathVariable String user) {
    try {
        List<Repo> repos = github_service.FetchReps(user);
        return ResponseEntity.ok(repos);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                Map.of("status", 404, "message", "User not found.")
        );
    }
}
}
