package com.project.application.model.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubBranch {
    @JsonProperty("name")
    String name;

    String commitSha;

    @JsonProperty("commit")
    private void setCommitSha(Map<String, String> commit) {
        commitSha = commit.get("sha");
    }
}
