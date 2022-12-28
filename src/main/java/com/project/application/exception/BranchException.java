package com.project.application.exception;

public class BranchException extends AbstractGithubError {

    public BranchException(String username, Throwable ex) {
        super(String.format("Could not get branches for repository %s, error message: %s", username, ex.getMessage()));
    }
}
