package com.unstoppedable.sharding.service;

public interface MsgHistoryService  {

    void initEnvironment();

    void testAddMsgHistroy();

    void testSelect();

    void clearData();

    void dropTables();
}
