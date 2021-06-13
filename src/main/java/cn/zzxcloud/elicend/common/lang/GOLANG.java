package cn.zzxcloud.elicend.common.lang;

public class GOLANG extends Lang {

    public GOLANG() {
        this.make();
    }

    @Override
    public void make() {
        this.setEnv(new String[] {"GO111MODULE=on","GOPROXY=https://goproxy.io","CGO_ENABLED=0","GOOS=linux"});
        this.setBaseCmd("cd /app;go build -o app;./app;");
        this.setBaseImage("library/golang:");
    }
}
