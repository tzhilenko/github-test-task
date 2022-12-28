package com.project.application.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Branch {

    String name;

    String commitSha;
}
