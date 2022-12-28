package com.project.application.model.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubRepository {

    @JsonProperty("name")
    String name;

    String owner;

    @JsonProperty("fork")
    boolean fork;

    @JsonProperty("owner")
    private void setOwner(Map<String, String> owner) {
        this.owner = owner.get("login");
    }
}
