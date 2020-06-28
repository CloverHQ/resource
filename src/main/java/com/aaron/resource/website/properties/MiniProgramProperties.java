package com.aaron.resource.website.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mini.program")
@Data
public class MiniProgramProperties {
    private String appId;
    private String appSecret;
}
