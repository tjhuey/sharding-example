package com.unstoppedable.sharding.algorithm;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Collection;
import java.util.Date;

public class MsgHistoryPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {


    @Override
    public String doSharding(final Collection<String> tableNames, final PreciseShardingValue<Date> shardingValue) {
        Date date = shardingValue.getValue();
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        for (String each : tableNames) {
            int year = localDateTime.get(ChronoField.YEAR);
            int month = localDateTime.get(ChronoField.MONTH_OF_YEAR);
            int index = AlgorithmUtils.getTableIndex(month);
            if (each.endsWith(year + "_" + index)) {
                return each;
            }
        }

        throw new UnsupportedOperationException("can't get the table names by sharding value");

    }





}
