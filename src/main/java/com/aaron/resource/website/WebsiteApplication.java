package com.aaron.resource.website;

import com.aaron.resource.website.properties.AliOssProperties;
import com.aaron.resource.website.properties.MiniProgramProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        AliOssProperties.class,
        MiniProgramProperties.class
})
public class WebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }

}
