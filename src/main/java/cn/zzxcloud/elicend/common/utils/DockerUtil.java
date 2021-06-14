package cn.zzxcloud.elicend.common.utils;

import cn.zzxcloud.elicend.api.entity.BuildHistory;
import cn.zzxcloud.elicend.api.mapper.BuildHistoryMapper;
import cn.zzxcloud.elicend.common.constant.Constant;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import static com.github.dockerjava.api.model.AccessMode.rw;
import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

@Component
public class DockerUtil {

    @Autowired
    private  BuildHistoryMapper bh;

    private static BuildHistoryMapper buildHistoryMapper;


    DockerClient client;

    @PostConstruct
    public void init() {
        buildHistoryMapper = this.bh;
    }

    public DockerUtil(){
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerTlsVerify(false)
                .withDockerHost(Constant.DOCKER_HOST)
                .build();

        this.client = DockerClientBuilder.getInstance(config).build();
        System.out.println("Docker connected");
    }
    public void pullImage(String image,int buildId){

        BuildHistory buildHistory = buildHistoryMapper.getById(buildId);
        buildHistory.setBuildMsg("Remove image: {"+ image +"}");
        System.out.println("Remove image: {"+ image +"}");
        buildHistoryMapper.update(buildHistory);

        try {
            this.client.removeImageCmd(image).withForce(true).exec();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        buildHistory.setBuildMsg(buildHistory.getBuildMsg() + "</br>Pulling image: {"+ image +"}");
        System.out.println("Pulling image: {"+ image +"}");
        buildHistoryMapper.update(buildHistory);

        try {
            this.client.pullImageCmd(image).start().awaitCompletion();

            buildHistory.setBuildMsg(buildHistory.getBuildMsg() + "</br>Image {"+ image +"} pull success");
            System.out.println("Image {"+ image +"} pull success");
            buildHistoryMapper.update(buildHistory);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public String createContainer(String image,int bindPort,int innerPort,String pathOnHost,String cmd,String[] env,int buildId) {
        BuildHistory buildHistory = buildHistoryMapper.getById(buildId);

        //端口绑定
        ExposedPort exposedPort = ExposedPort.tcp(innerPort);
        Ports portBindings = new Ports();
        portBindings.bind(exposedPort, Ports.Binding.bindPort(bindPort));
        Volume volume = new Volume("/app");
        String containerId="";
        try {
            CreateContainerResponse container = this.client.createContainerCmd(image)
                    .withEnv(env)
                    .withCmd("/bin/sh", "-c", cmd + "while true; do echo hello world; sleep 1; done")
                    .withExposedPorts(exposedPort)
                    .withVolumes(volume)
                    .withHostConfig(newHostConfig()
                            .withPortBindings(portBindings)
                            .withBinds(new Bind(pathOnHost, volume, rw))
                    )
                    .exec();

            buildHistory.setBuildMsg(buildHistory.getBuildMsg() + "</br>Created container {" + container.toString() + "}");
            System.out.println("Created container {" + container.toString() + "}");
            buildHistoryMapper.update(buildHistory);

            containerId = container.getId();
            this.client.startContainerCmd(containerId).exec();
        }catch (Exception e){

            buildHistory.setBuildMsg(buildHistory.getBuildMsg() +"</br>"+ e.toString());
            buildHistory.setState(-1);
            buildHistoryMapper.update(buildHistory);

            e.printStackTrace();
            return null;
        }
        return containerId;
    }

    public void removeContainer(String containerId,int buildId){
        BuildHistory buildHistory = buildHistoryMapper.getById(buildId);
        try{
            buildHistory.setBuildMsg(buildHistory.getBuildMsg() + "</br>stop container {" + containerId + "}");
            System.out.println("stop container {" + containerId + "}");
            buildHistoryMapper.update(buildHistory);
            this.client.stopContainerCmd(containerId).exec();
            buildHistory.setBuildMsg(buildHistory.getBuildMsg() + "</br>remove container {" + containerId + "}");
            System.out.println("remove container {" + containerId + "}");
            buildHistoryMapper.update(buildHistory);
            this.client.removeContainerCmd(containerId).exec();
        }catch (DockerException e){
            buildHistory.setBuildMsg(buildHistory.getBuildMsg() +"</br>"+ e.toString());
            buildHistory.setState(-1);
            buildHistoryMapper.update(buildHistory);
            e.printStackTrace();
        }
    }

}
