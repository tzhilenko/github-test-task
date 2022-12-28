package com.project.application.mapping;

import com.project.application.model.entity.Branch;
import com.project.application.model.entity.Repository;
import com.project.application.model.github.GithubBranch;
import com.project.application.model.github.GithubRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper
public interface GithubInstanceMapper {

    GithubInstanceMapper INSTANCE = Mappers.getMapper(GithubInstanceMapper.class);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "owner", target = "owner"),
            @Mapping(source = "fork", target = "fork"),
    })
    Repository mapRepo(GithubRepository githubRepository);

    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "commitSha", target = "commitSha"),
    })
    Branch mapBranch(GithubBranch githubBranch);

}
