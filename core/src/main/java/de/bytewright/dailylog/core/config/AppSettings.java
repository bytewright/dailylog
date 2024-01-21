package de.bytewright.dailylog.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;

@Service
public class AppSettings {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettings.class);
    @Autowired
    private YAMLConfig yamlConfig;

    public Path getBaseProjectPath() {
        String basedir = yamlConfig.getBasedir();
        Path basePath;
        if (basedir != null) {
            basePath = Path.of(basedir).toAbsolutePath().normalize();
        } else {
            basePath = new File("").toPath().toAbsolutePath();
        }
        return basePath;
    }

}
