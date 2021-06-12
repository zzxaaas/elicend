package cn.zzxcloud.elicend.common.vo;

import lombok.Data;

import java.io.Serializable;

@Data
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

    public GitVO(GitVOBuilder builder) {
        this.localRepoPath = builder.localRepoPath;
        this.localRepoGitConfig = builder.localRepoGitConfig;
        this.remoteRepoUri = builder.remoteRepoUri;
        this.initLocalCodeDir = builder.initLocalCodeDir;
        this.localCodeCTSqlDir = builder.localCodeCTSqlDir;
        this.branchName = builder.branchName;
        this.gitUsername = builder.gitUsername;
        this.gitPassword = builder.gitPassword;
    }

    public static class GitVOBuilder {
        private String localRepoPath;
        private String localRepoGitConfig;
        private String remoteRepoUri;
        private String initLocalCodeDir;
        private String localCodeCTSqlDir;
        private String branchName;
        private String gitUsername;
        private String gitPassword;

        public GitVOBuilder(String gitUsername, String gitPassword) {
            this.gitUsername = gitUsername;
            this.gitPassword = gitPassword;
        }

        public GitVOBuilder setLocalRepoPath(String localRepoPath) {
            this.localRepoPath = localRepoPath;
            return this;
        }

        public GitVOBuilder setLocalRepoGitConfig(String localRepoGitConfig) {
            this.localRepoGitConfig = localRepoGitConfig;
            return this;
        }

        public GitVOBuilder setRemoteRepoUri(String remoteRepoUri) {
            this.remoteRepoUri = remoteRepoUri;
            return this;
        }

        public GitVOBuilder setInitLocalCodeDir(String initLocalCodeDir) {
            this.initLocalCodeDir = initLocalCodeDir;
            return this;
        }

        public GitVOBuilder setLocalCodeCTSqlDir(String localCodeCTSqlDir) {
            this.localCodeCTSqlDir = localCodeCTSqlDir;
            return this;
        }

        public GitVOBuilder setBranchName(String branchName) {
            this.branchName = branchName;
            return this;
        }

        public GitVO build() {
            return new GitVO(this);
        }
    }
}
