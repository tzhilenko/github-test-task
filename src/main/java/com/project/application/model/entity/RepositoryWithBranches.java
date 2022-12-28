package com.project.application.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class RepositoryWithBranches {

    String name;

    String owner;

    List<Branch> branches;
}
