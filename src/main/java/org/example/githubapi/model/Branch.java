package org.example.githubapi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Branch {

    @JsonAlias("name")
    @JsonProperty("branchName")
    private String branchName;

    @JsonProperty("commit")
    private Commit commit; //commit variable which stores information about the latest commit


    public String getBranchName() {
        return branchName;
    }


    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }


    public Commit getCommit() {
        return commit;
    }


    public void setCommit(Commit commit) {
        this.commit = commit;
    }
}
