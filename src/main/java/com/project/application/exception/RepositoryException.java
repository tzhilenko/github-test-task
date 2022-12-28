package com.project.application.exception;

public class RepositoryException extends AbstractGithubError {

    public RepositoryException(String username, Throwable ex) {
        super(String.format("Could not get repositories for %s, error message: %s", username, ex.getMessage()));
    }
}
