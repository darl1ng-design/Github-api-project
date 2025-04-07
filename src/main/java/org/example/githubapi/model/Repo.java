package org.example.githubapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Repo {
    @JsonAlias("name")
    @JsonProperty("repoName")
    private String repoName;

    @JsonProperty("owner")
    private Owner owner;

    @JsonProperty("fork")
    private boolean fork; // whether the repository is forked or not

    @JsonProperty("branches")
    private List<Branch> branches;

    public String getRepoName() {
        return repoName;
    }


    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }


    public Owner getOwner() {
        return owner;
    }


    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    public boolean isFork() {
        return fork;
    }


    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
}