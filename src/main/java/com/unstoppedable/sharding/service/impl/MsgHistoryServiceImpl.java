package com.unstoppedable.sharding.service.impl;

import com.unstoppedable.sharding.manager.MsgHistoryManager;
import com.unstoppedable.sharding.model.MsgHistory;
import com.unstoppedable.sharding.service.MsgHistoryService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class MsgHistoryServiceImpl implements MsgHistoryService {

    @Resource
    private MsgHistoryManager msgHistoryManager;

    @Override
    public void initEnvironment() {
        msgHistoryManager.createTableIfNotExists();
    }

    @Override
    public void testAddMsgHistroy() {
        Calendar calendar = Calendar.getInstance();
        List<MsgHistory> msgHistoryList = new ArrayList<>();
        for (int i=0; i < 100; i++) {
            MsgHistory msgHistory = new MsgHistory();
            msgHistory.setMsgContent("test msg content" + i);

            if (i > 50) {
                calendar.set(Calendar.YEAR, 2019);
            }
            calendar.set(Calendar.MONTH, i % 12);
            msgHistory.setMsgCreateTime(calendar.getTime());
            msgHistoryList.add(msgHistory);
        }

        msgHistoryManager.addRecords(msgHistoryList);
    }

    @Override
    public void testSelect() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 0);

        Date from1 = calendar.getTime();
        calendar.set(Calendar.MONTH, 2);
        Date to1 = calendar.getTime();

//        System.out.println("------------------------selectByMsgCreateTime-----------------------");
//        //from1 to1 在数据在一个表中，查询的时候，看sharding jdbc 发出几条查询语句，有没有定位表发出请求，而不是发送全部
//        msgHistoryManager.selectByMsgCreateTime(from1);
//        System.out.println("------------------------selectByRange in one shard-----------------------");
//        msgHistoryManager.selectByRange(from1, to1);

        System.out.println("------------------------selectPageByRange in one shard-----------------------");
        msgHistoryManager.selectPageByRange(from1, to1,100 ,10);


        //from1 to1 在数据【不在】一个表中
        calendar.set(Calendar.MONTH, 6);
        Date to2 = calendar.getTime();
//        System.out.println("------------------------selectByMsgCreateTime-----------------------");
//        msgHistoryManager.selectByMsgCreateTime(to2);
//
//
//        System.out.println("------------------------selectByRange in different shard-----------------------");
////
//        msgHistoryManager.selectByRange(from1, to2);

        System.out.println("------------------------selectPageByRange in different shard-----------------------");
        msgHistoryManager.selectPageByRange(from1, to2,10 ,10);


    }

    @Override
    public void clearData() {

        msgHistoryManager.cleanData();
    }

    @Override
    public void dropTables() {
        msgHistoryManager.dropTable();
    }


}
