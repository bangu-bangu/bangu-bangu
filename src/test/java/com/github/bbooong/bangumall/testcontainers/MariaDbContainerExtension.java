package com.github.bbooong.bangumall.testcontainers;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MariaDBContainer;

public class MariaDbContainerExtension implements BeforeAllCallback {

    static final MariaDBContainer<?> MARIADB_CONTAINER = new MariaDBContainer<>("mariadb:latest");

    @Override
    public void beforeAll(final ExtensionContext context) throws Exception {
        if (MARIADB_CONTAINER.isRunning()) {
            return;
        }
        MARIADB_CONTAINER.start();

        System.setProperty(
                "spring.datasource.url", MARIADB_CONTAINER.getJdbcUrl() + "?useAffectedRows=true");
        System.setProperty("spring.datasource.username", MARIADB_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", MARIADB_CONTAINER.getPassword());
    }
}
