package com.unstoppedable.sharding.algorithm;

import com.google.common.collect.Range;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class MsgHistoryRangeShardingAlgorithm implements RangeShardingAlgorithm<Date> {


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Date> shardingValue) {
        Range<Date> range = shardingValue.getValueRange();
        Date startDate = range.lowerEndpoint();
        Date endDate = range.upperEndpoint();

        LocalDateTime start = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime end = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

        HashSet<String> matchTables = new HashSet<>();
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException(shardingValue.getColumnName() + ":startDate not before endDate !");
        }

        while (end.isAfter(start)) {
            int year = start.get(ChronoField.YEAR);
            int month = start.get(ChronoField.MONTH_OF_YEAR);
            long index = AlgorithmUtils.getTableIndex(month);
            for (String availableTargetName : availableTargetNames) {
                if (availableTargetName.endsWith(year + "_" + index)) {
                    matchTables.add(availableTargetName);
                    break;
                }
            }
            start = start.plusMonths(1);
        }
        return matchTables;
    }
}
