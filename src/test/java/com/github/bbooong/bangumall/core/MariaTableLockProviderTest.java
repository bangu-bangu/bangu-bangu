package com.github.bbooong.bangumall.core;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.github.bbooong.bangumall.testcontainers.MariaDbTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@DisplayName("단위 테스트: MariaTableLockProvider")
@MariaDbTest
@JdbcTest
class MariaTableLockProviderTest {

    @Autowired JdbcTemplate jdbcTemplate;

    MariaTableLockProvider mariaTableLockProvider;

    @BeforeEach
    void setUp() {
        mariaTableLockProvider = new MariaTableLockProvider(jdbcTemplate);
    }

    @Nested
    @DisplayName("Lock을 획득하려고 할 때")
    class Describe_AcquireLock {

        @Nested
        @DisplayName("동일한 이름의 Lock이 존재하지 않을 때")
        class Context_With_NotExistLock {

            @Test
            @DisplayName("true를 반환한다.")
            void it_returns_true() {
                assertThat(mariaTableLockProvider.acquireLock("lock:123", 1)).isTrue();
            }
        }

        @Nested
        @DisplayName("만료 시간이 지나지 않은 동일한 이름의 Lock이 존재할 때")
        class Context_With_ExistLock {

            @BeforeEach
            void setUp() {
                mariaTableLockProvider.acquireLock("lock:123", 1000);
            }

            @Test
            @DisplayName("false를 반환한다.")
            void it_returns_false() {
                assertThat(mariaTableLockProvider.acquireLock("lock:123", 1)).isFalse();
            }
        }

        @Nested
        @DisplayName("만료 시간이 지난 동일한 이름의 Lock이 존재할 때")
        class Context_With_ExpiredLock {

            @BeforeEach
            void setUp() {
                mariaTableLockProvider.acquireLock("lock:123", 0);
            }

            @Test
            @DisplayName("true를 반환한다.")
            void it_returns_true() {
                assertThat(mariaTableLockProvider.acquireLock("lock:123", 1)).isTrue();
            }
        }
    }

    @Nested
    @DisplayName("Lock을 해제하려고 할 때")
    class Describe_ReleaseLock {

        @Nested
        @DisplayName("동일한 이름의 Lock이 존재할 때")
        class Context_With_ExistLock {

            @BeforeEach
            void setUp() {
                mariaTableLockProvider.acquireLock("lock:123", 1000);
            }

            @Test
            @DisplayName("Lock을 해제한다.")
            void it_releases_lock() {
                mariaTableLockProvider.releaseLock("lock:123");

                assertThatCode(() -> mariaTableLockProvider.acquireLock("lock:123", 1))
                        .doesNotThrowAnyException();
            }
        }
    }
}
