package com.unstoppedable.sharding.dao;

import com.unstoppedable.sharding.model.MsgHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface MsgHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MsgHistory record);

    MsgHistory selectByPrimaryKey(Long id);

    List<MsgHistory> selectAll();

    int updateByPrimaryKey(MsgHistory record);

    void dropTable();

    void createTableIfNotExists();

    void truncateTable();

    List<MsgHistory> selectByDateRange(@Param("from") Date from, @Param("to") Date to);

    List<MsgHistory> selectByMsgCreateTime(Date msgCreateTime);


    List<MsgHistory> selectPageByRange(@Param("from") Date from, @Param("to") Date to, @Param("startIndex") int startIndex, @Param("size") int pageSize);

}