package com.pobreng.programmer.model;

public class Config {
    private String applicationName;
    private String githubRepositoryName;
    private String githubUsername;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getGithubRepositoryName() {
        return githubRepositoryName;
    }

    public void setGithubRepositoryName(String githubRepositoryName) {
        this.githubRepositoryName = githubRepositoryName;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public void setGithubUsername(String githubUsername) {
        this.githubUsername = githubUsername;
    }
}
