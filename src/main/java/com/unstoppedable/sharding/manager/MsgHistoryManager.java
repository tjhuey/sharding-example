package com.unstoppedable.sharding.manager;

import com.unstoppedable.sharding.model.MsgHistory;

import java.util.Date;
import java.util.List;

public interface MsgHistoryManager {

    List<Long> addRecords(List<MsgHistory> msgHistories);

    List<MsgHistory> selectByRange(Date from, Date to);

    List<MsgHistory> selectPageByRange(Date from, Date to, int startIndex, int pageSize);

    List<MsgHistory> selectByMsgCreateTime(Date localDateTime);

    void createTableIfNotExists();

    void cleanData();

    void dropTable();
}
