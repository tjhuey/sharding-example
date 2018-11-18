package com.unstoppedable.sharding;

import com.unstoppedable.sharding.service.MsgHistoryService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {


    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-sharding-tables.xml")) {
            MsgHistoryService msgHistoryService = applicationContext.getBean(MsgHistoryService.class);

//            msgHistoryService.dropTables();
//            msgHistoryService.initEnvironment();
//
//            msgHistoryService.testAddMsgHistroy();

            msgHistoryService.testSelect();

//            msgHistoryService.clearDate();
        }
    }
}
