package de.bytewright.dailylog.core.persistance.config;

import de.bytewright.dailylog.core.persistance.repos.RepoMarker;
import org.slf4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = RepoMarker.class)
public class PersistenceConfig {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PersistenceConfig.class);
}
