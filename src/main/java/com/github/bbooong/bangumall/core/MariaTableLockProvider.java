package com.github.bbooong.bangumall.core;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MariaTableLockProvider implements LockProvider {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean acquireLock(final String name, final int seconds) {
        try {
            final int updatedRows =
                    jdbcTemplate.update(
                            "INSERT INTO locks (name, expired_at) "
                                    + "VALUES (?, DATE_ADD(NOW(), INTERVAL ? SECOND)) "
                                    + "ON DUPLICATE KEY UPDATE expired_at = IF(expired_at <= NOW(), DATE_ADD(NOW(), INTERVAL ? SECOND), expired_at)",
                            name,
                            seconds,
                            seconds);

            return updatedRows > 0;
        } catch (final DataAccessException e) {
            return false;
        }
    }

    @Override
    public void releaseLock(final String name) {
        jdbcTemplate.update("DELETE FROM locks WHERE name = ?", name);
    }
}
