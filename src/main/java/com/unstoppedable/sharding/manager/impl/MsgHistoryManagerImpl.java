package com.unstoppedable.sharding.manager.impl;

import com.unstoppedable.sharding.dao.MsgHistoryMapper;
import com.unstoppedable.sharding.manager.MsgHistoryManager;
import com.unstoppedable.sharding.model.MsgHistory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class MsgHistoryManagerImpl implements MsgHistoryManager {

    @Resource
    private MsgHistoryMapper msgHistoryMapper;


    @Override
    @Transactional
    public List<Long> addRecords(List<MsgHistory> msgHistories) {
        return msgHistories.stream().map(msgHistory -> {
            msgHistoryMapper.insert(msgHistory);
            return msgHistory.getId();
        }).collect(Collectors.toList());

    }

    @Override
    public List<MsgHistory> selectByRange(Date from, Date to) {
        return msgHistoryMapper.selectByDateRange(from, to);
    }

    @Override
    public List<MsgHistory> selectPageByRange(Date from, Date to, int startIndex, int pageSize) {
        return msgHistoryMapper.selectPageByRange(from, to, startIndex, pageSize);
    }


    @Override
    public List<MsgHistory> selectByMsgCreateTime(Date localDateTime) {
        return msgHistoryMapper.selectByMsgCreateTime(localDateTime);
    }

    @Override
    public void createTableIfNotExists() {
        msgHistoryMapper.createTableIfNotExists();
    }

    @Override
    public void cleanData() {
        msgHistoryMapper.truncateTable();
    }

    @Override
    public void dropTable() {
        msgHistoryMapper.dropTable();
    }


}
