package com.zjl.washer.Config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {
}
