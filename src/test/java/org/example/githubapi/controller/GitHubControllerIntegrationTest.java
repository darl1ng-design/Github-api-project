package org.example.githubapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GitHubControllerIntegrationTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnNonForkedReposWithBranchesAndSha() throws Exception {
        mockMvc.perform(get("/api/github/repos/darl1ng-design"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].repoName", not(emptyOrNullString())))
                .andExpect(jsonPath("$[0].owner.login", not(emptyOrNullString())))
                .andExpect(jsonPath("$[0].branches",hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].branches[0].branchName", not(emptyOrNullString())))
                .andExpect(jsonPath("$[0].branches[0].commit.sha", not(emptyOrNullString())));
    }


    @Test
    public void shouldReturn404ForNonExistentUser() throws Exception {
        mockMvc.perform(get("/api/github/repos/dsksfdkfskisfdjh39bnaskdsabask@#$"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.message", is("User not found.")));
    }

}