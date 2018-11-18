package com.unstoppedable.sharding.algorithm;

import io.shardingsphere.core.keygen.KeyGenerator;

import java.util.concurrent.atomic.AtomicLong;

public class TestKeyGenerator implements KeyGenerator {

    private AtomicLong init = new AtomicLong(1);
    @Override
    public Number generateKey() {
        return init.getAndIncrement();
    }
}
