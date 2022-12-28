package com.project.application.controller;

import com.project.application.model.entity.RepositoryWithBranches;
import com.project.application.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/github")
public class GithubController {

    GithubService githubService;

    public GithubController(GithubService githubService) {
        this.githubService = githubService;
    }

    @GetMapping(value = "/repositories/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<RepositoryWithBranches> list(@PathVariable String username) {
        return githubService.getRepositoriesWithBranches(username);
    }

}
