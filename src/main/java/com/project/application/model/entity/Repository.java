package com.project.application.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Repository {

    String name;

    String owner;

    boolean fork;
}
