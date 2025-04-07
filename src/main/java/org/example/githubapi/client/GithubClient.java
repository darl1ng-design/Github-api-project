package org.example.githubapi.client;

import org.example.githubapi.model.Branch;
import org.example.githubapi.model.Repo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@Component
public class GithubClient {

    private final RestTemplate restTemplate;

    public GithubClient(RestTemplate template){
        this.restTemplate = template;
    }

    //fetch non forked repositories for the specific user
    public List<Repo> getRepos(String user){

        String url = UriComponentsBuilder.fromUriString("https://api.github.com")
                .path("/users/{user}/repos")
                .queryParam("type", "owner") //non-forked repos only
                .buildAndExpand(user)
                .toUriString();

        //send get request and map response to list of repos
        Repo[] repos = restTemplate.getForObject(url, Repo[].class);
        return repos != null ? List.of(repos) : List.of();
    }

    //fetch branch from the specific repo
    public List<Branch> getBranches(String user, String repo){
        String url = UriComponentsBuilder.fromUriString("https://api.github.com")
                .path("/repos/{user}/{repo}/branches")
                .queryParam("type", "owner")
                .buildAndExpand(user, repo)
                .toUriString();

        //send get request and map response to list of branches
        Branch[] branches = restTemplate.getForObject(url, Branch[].class);
        return branches != null ? List.of(branches) : List.of();
    }
}
