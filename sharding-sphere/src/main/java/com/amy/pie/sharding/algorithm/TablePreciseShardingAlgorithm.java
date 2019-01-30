package com.amy.pie.sharding.algorithm;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;
import java.util.Random;

/**
 * Author : liuby.
 * Description : ==和IN的分表算法实现
 * Date : Created in 2019/1/29 16:23
 */
public final class TablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    private Random r = new Random();

    @Override
    public String doSharding(final Collection<String> availableTargetNames, final PreciseShardingValue<Long> shardingValue) {
        Long id = shardingValue.getValue() + r.nextInt(2);
        int size = availableTargetNames.size();
        for (String each : availableTargetNames) {
            if (each.endsWith(id % size + "")) {
                return each;
            }
        }
        throw new UnsupportedOperationException();
    }
}
