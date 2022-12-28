package com.project.application.exception;

public abstract class AbstractGithubError extends RuntimeException {

    public AbstractGithubError(String message) {
        super(message);
    }

    public AbstractGithubError(String message, Throwable cause) {
        super(message, cause);
    }
}
