package de.bytewright.dailylog.core.services;

import de.bytewright.dailylog.core.config.AppSettings;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

@Service
@Profile("!test")
@RequiredArgsConstructor
public class SecretsService implements HealthIndicator, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(SecretsService.class);
    private static final String FILENAME = "app.secret";
    private static final String HEALTHKEY = "test.key";
    private static final String HEALTHKEYVALUE = "testkey";
    private final Properties props = new Properties();
    private final AppSettings appSettings;


    public void init() {
        Path path = appSettings.getBaseProjectPath().resolve(FILENAME);
        if (!Files.isReadable(path)) {
            throw new IllegalStateException("Can't read secrets file at " + path.toAbsolutePath());
        }
        // Load the secretfile from the current directory
        try (FileInputStream fis = new FileInputStream(path.toFile())) {
            props.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Get the values of some secrets
        String property = props.getProperty(HEALTHKEY);
        if (!HEALTHKEYVALUE.equals(property)) {
            throw new IllegalStateException("secretfile does not contain healthkey");
        }
    }

    @Override
    public Health health() {
        if (props.isEmpty()) {
            return Health.down().withDetail("secretfile", "empty or not found").build();
        }
        return Health.up().build();
    }

    public Optional<String> getString(String key) {
        return Optional.ofNullable(props.getProperty(key));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Loading app secrets...");
        init();
        log.info("Loading app secrets finished, got {} entries", props.size());
    }
}
