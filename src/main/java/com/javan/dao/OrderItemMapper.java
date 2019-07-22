package com.javan.dao;

import com.javan.entity.OrderItem;
import com.javan.entity.OrderItemExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
    int countByExample(OrderItemExample example);

    int deleteByExample(OrderItemExample example);

    int deleteByPrimaryKey(Integer order_id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Integer order_id);

    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemExample example);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    Float countAmountByCondition(@Param("cloth_id") Integer cloth_id, @Param("startTime")Date startTime, @Param("endTime")Date  endTime, @Param("size")String size,@Param("customer_order")String customer_order);  //总金额

    Integer countNumberByCondition(@Param("cloth_id") Integer cloth_id, @Param("startTime")Date startTime, @Param("endTime")Date  endTime, @Param("size")String size,@Param("customer_order")String customer_order);  //总件数

    List<Integer> showCloth(@Param("startTime")Date startTime, @Param("endTime")Date  endTime,@Param("customer_order")String customer_order);
}