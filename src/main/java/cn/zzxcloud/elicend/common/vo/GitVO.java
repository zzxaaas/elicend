package cn.zzxcloud.elicend.common.vo;

import java.io.Serializable;

public class GitVO implements Serializable {
    private String localRepoPath ;
    private String localRepoGitConfig;
    private String remoteRepoUri;
    private String initLocalCodeDir;
    private String localCodeCTSqlDir;
    private String branchName ;
    private String gitUsername;
    private String gitPassword ;


    public GitVO() {
    }

    public GitVO(String localRepoPath, String remoteRepoUri, String gitUsername, String gitPassword) {
        this.localRepoPath = localRepoPath;
        this.remoteRepoUri = remoteRepoUri;
        this.gitUsername = gitUsername;
        this.gitPassword = gitPassword;
    }

    public GitVO(String localRepoPath, String remoteRepoUri, String branchName, String gitUsername, String gitPassword) {
        this.localRepoPath = localRepoPath;
        this.remoteRepoUri = remoteRepoUri;
        this.branchName = branchName;
        this.gitUsername = gitUsername;
        this.gitPassword = gitPassword;
    }

    public String getLocalRepoPath() {
        return localRepoPath;
    }

    public void setLocalRepoPath(String localRepoPath) {
        this.localRepoPath = localRepoPath;
    }

    public String getLocalRepoGitConfig() {
        return localRepoGitConfig;
    }

    public void setLocalRepoGitConfig(String localRepoGitConfig) {
        this.localRepoGitConfig = localRepoGitConfig;
    }

    public String getRemoteRepoUri() {
        return remoteRepoUri;
    }

    public void setRemoteRepoUri(String remoteRepoUri) {
        this.remoteRepoUri = remoteRepoUri;
    }

    public String getInitLocalCodeDir() {
        return initLocalCodeDir;
    }

    public void setInitLocalCodeDir(String initLocalCodeDir) {
        this.initLocalCodeDir = initLocalCodeDir;
    }

    public String getLocalCodeCTSqlDir() {
        return localCodeCTSqlDir;
    }

    public void setLocalCodeCTSqlDir(String localCodeCTSqlDir) {
        this.localCodeCTSqlDir = localCodeCTSqlDir;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getGitUsername() {
        return gitUsername;
    }

    public void setGitUsername(String gitUsername) {
        this.gitUsername = gitUsername;
    }

    public String getGitPassword() {
        return gitPassword;
    }

    public void setGitPassword(String gitPassword) {
        this.gitPassword = gitPassword;
    }
}
