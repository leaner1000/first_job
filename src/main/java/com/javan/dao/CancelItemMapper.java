package com.javan.dao;

import com.javan.entity.CancelItem;
import com.javan.entity.CancelItemExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CancelItemMapper {
    int countByExample(CancelItemExample example);

    int deleteByExample(CancelItemExample example);

    int deleteByPrimaryKey(Integer cancelitem_id);

    int insert(CancelItem record);

    int insertSelective(CancelItem record);

    List<CancelItem> selectByExample(CancelItemExample example);

    CancelItem selectByPrimaryKey(Integer cancelitem_id);

    int updateByExampleSelective(@Param("record") CancelItem record, @Param("example") CancelItemExample example);

    int updateByExample(@Param("record") CancelItem record, @Param("example") CancelItemExample example);

    int updateByPrimaryKeySelective(CancelItem record);

    int updateByPrimaryKey(CancelItem record);

    Float countAmountByCondition(@Param("cloth_id") Integer cloth_id, @Param("startTime")Date startTime, @Param("endTime")Date  endTime, @Param("size")String size,@Param("customer_cancel")String customer_order);  //总金额

    Integer countNumberByCondition(@Param("cloth_id") Integer cloth_id, @Param("startTime")Date startTime, @Param("endTime")Date  endTime, @Param("size")String size,@Param("customer_cancel")String customer_order);  //总件数

    List<Integer> showCloth(@Param("startTime")Date startTime, @Param("endTime")Date  endTime,@Param("customer_cancel")String customer_order);

}