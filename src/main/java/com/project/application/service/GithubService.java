package com.project.application.service;

import com.project.application.exception.BranchException;
import com.project.application.exception.RepositoryException;
import com.project.application.exception.UserNotFoundException;
import com.project.application.model.entity.RepositoryWithBranches;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GithubService {

    GithubBrunchesRemoteService branches;

    GithubRepositoriesRemoteService repositories;

    public GithubService(GithubBrunchesRemoteService branches, GithubRepositoriesRemoteService repositories) {
        this.branches = branches;
        this.repositories = repositories;
    }

    public Flux<RepositoryWithBranches> getRepositoriesWithBranches(String username) {
        return repositories.getRepositoriesByUser(username)
                .filter(repository -> !repository.isFork())
                .onErrorMap(e -> !(e instanceof UserNotFoundException), e -> new RepositoryException(username, e))
                .flatMap(repository -> {
                    return branches.getBranchesByRepository(repository.getOwner(), repository.getOwner())
                            .collectList()
                            .onErrorMap(e -> new BranchException(repository.getName(), e))
                            .map(branchValues -> new RepositoryWithBranches(repository.getName(), repository.getOwner(), branchValues));
                });
    }

}
