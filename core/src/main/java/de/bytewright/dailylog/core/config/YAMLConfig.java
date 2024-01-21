package de.bytewright.dailylog.core.config;

import lombok.Data;
import org.slf4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class YAMLConfig implements InitializingBean {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(YAMLConfig.class);
    private String name;
    private String basedir;

    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("Using config: {}", this);
    }
}
