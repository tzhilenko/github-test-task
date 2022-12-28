package com.project.application.mapping;

import com.project.application.model.entity.Branch;
import com.project.application.model.entity.Repository;
import com.project.application.model.github.GithubBranch;
import com.project.application.model.github.GithubRepository;
import org.junit.jupiter.api.Test;

import static com.project.application.utils.BranchUtils.validateBranchMapping;
import static com.project.application.utils.GithubBranchUtils.getGithubBranch;
import static com.project.application.utils.GithubRepositoryUtils.getRepository;
import static com.project.application.utils.RepositoryUtils.validateRepositoryMapping;

public class GithubInstanceMapperTest {

    @Test
    void shouldMapGithubBranchToEntity() {

        GithubBranch githubBranch = getGithubBranch();
        Branch branch = GithubInstanceMapper.INSTANCE.mapBranch(githubBranch);

        validateBranchMapping(githubBranch, branch);

    }

    @Test
    void shouldMapGithubRepositoryToEntity() {

        GithubRepository githubRepository = getRepository();
        Repository repository = GithubInstanceMapper.INSTANCE.mapRepo(githubRepository);

        validateRepositoryMapping(githubRepository, repository);

    }
}
