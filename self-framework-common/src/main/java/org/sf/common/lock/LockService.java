package org.sf.common.lock;

/**
 * 锁支持
 * @author zx
 */
public interface LockService {

    void lock(String lockKey, long lockTime, LockRun lockRun);

    @FunctionalInterface
    interface LockRun {
        void run();
    }
}
