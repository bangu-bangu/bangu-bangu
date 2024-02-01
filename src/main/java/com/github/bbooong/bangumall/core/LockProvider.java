package com.github.bbooong.bangumall.core;

public interface LockProvider {

    boolean acquireLock(String name, int seconds);

    void releaseLock(String name);
}
