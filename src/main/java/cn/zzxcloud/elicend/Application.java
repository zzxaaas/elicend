package cn.zzxcloud.elicend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
@MapperScan("cn.zzxcloud.elicend.api.mapper")
@SpringBootApplication
@ServletComponentScan
public class Application {

    public static void main(String[] args) {

        // Spring应用启动
        SpringApplication.run(Application.class, args);

    }


}