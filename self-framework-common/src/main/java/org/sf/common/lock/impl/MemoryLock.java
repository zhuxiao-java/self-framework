package org.sf.common.lock.impl;

import com.google.common.util.concurrent.Striped;
import org.sf.common.lock.LockService;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author zhuxiao
 */
@SuppressWarnings("UnstableApiUsage")
public class MemoryLock implements LockService {

    private final Striped<Lock> striped;

    public MemoryLock(int capacity) {
        this.striped = Striped.lock(capacity);
    }

    public MemoryLock() {
        this(1024);
    }

    @Override
    public void lock(String lockKey, long lockTime, LockRun lockRun) {
        try {
            boolean isLock = striped.get(lockKey).tryLock(lockTime, TimeUnit.MILLISECONDS);
            if (!isLock) {
                throw new IllegalMonitorStateException("lock time out");
            }
            lockRun.run();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            striped.get(lockKey).unlock();
        }
    }
}
