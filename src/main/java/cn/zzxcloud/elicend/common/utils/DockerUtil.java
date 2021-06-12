package cn.zzxcloud.elicend.common.utils;

import cn.zzxcloud.elicend.common.constant.Constant;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.command.InspectImageResponse;
import com.github.dockerjava.api.exception.NotFoundException;
import com.github.dockerjava.api.model.*;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

import static com.github.dockerjava.api.model.AccessMode.rw;
import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

public class DockerUtil {

    DockerClient client;
    public DockerUtil(){
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerTlsVerify(false)
                .withDockerHost("tcp://159.75.109.38:2375")
                .build();

        this.client = DockerClientBuilder.getInstance(config).build();
        System.out.println("Docker connected");
    }
    public void pullImage(String image){

        System.out.println("Remove image: {"+ image +"}");
        try {
            this.client.removeImageCmd(image).withForce(true).exec();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Pulling image: {"+ image +"}");
        try {
            this.client.pullImageCmd(image).start().awaitCompletion();
            System.out.println("Image {"+ image +"} pull success");
//            InspectImageResponse inspectImageResponse = this.client.inspectImageCmd(image).exec();
//            System.out.println("Image Inspect: {" + inspectImageResponse.toString() + "}");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void createContainer(String image,int bindPort,int innerPort,String pathOnHost,String cmd,String[] env) {

        //端口绑定
        ExposedPort exposedPort = ExposedPort.tcp(innerPort);
        Ports portBindings = new Ports();
        portBindings.bind(exposedPort, Ports.Binding.bindPort(bindPort));
        Volume volume = new Volume("/app");
        CreateContainerResponse container = this.client.createContainerCmd(image)
                .withEnv(env)
                .withCmd("/bin/sh", "-c", "cd /app;" + cmd + "while true; do echo hello world; sleep 1; done")
                .withExposedPorts(exposedPort)
                .withVolumes(volume)
                .withHostConfig(newHostConfig()
                        .withPortBindings(portBindings)
                        .withBinds(new Bind(pathOnHost, volume, rw))
                )
                .exec();
        System.out.println("Created container {" + container.toString() + "}");
        this.client.startContainerCmd(container.getId()).exec();
//        InspectContainerResponse inspectContainerResponse = this.client.inspectContainerCmd(container.getId()).exec();
//        System.out.println("Container Inspect: {" + inspectContainerResponse.toString() + "}");
    }

}
