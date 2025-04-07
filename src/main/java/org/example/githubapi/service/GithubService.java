package org.example.githubapi.service;

import org.example.githubapi.client.GithubClient;
import org.example.githubapi.model.Repo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GithubService {
    private final GithubClient client;

    public GithubService(GithubClient client) {
        this.client = client;
    }

    public List<Repo> FetchReps(String user){
        //fetch repositories from the GithubClient
        List<Repo> repos = client.getRepos(user);
        if (repos == null || repos.isEmpty()) {
            throw new RuntimeException("User not found or no repositories available");
        }
        //return only nonforked repositories
        List<Repo> nonForkedRepos = repos.stream()
                .filter(repo -> !repo.isFork())
                .toList();

        nonForkedRepos.forEach(
                repo -> {
                    try {
                        repo.setBranches(client.getBranches(user, repo.getRepoName()));
                    } catch (Exception e) {
                        throw new RuntimeException("Error fetching branches for repo: " + repo.getRepoName(), e);
                    }
                }
        );

        return nonForkedRepos;
    }

}
