package com.javan.dao;

import com.javan.entity.Cancel;
import com.javan.entity.CancelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CancelMapper {
    int countByExample(CancelExample example);

    int deleteByExample(CancelExample example);

    int deleteByPrimaryKey(Integer cancel_id);

    int insert(Cancel record);

    int insertSelective(Cancel record);

    List<Cancel> selectByExample(CancelExample example);

    Cancel selectByPrimaryKey(Integer cancel_id);

    int updateByExampleSelective(@Param("record") Cancel record, @Param("example") CancelExample example);

    int updateByExample(@Param("record") Cancel record, @Param("example") CancelExample example);

    int updateByPrimaryKeySelective(Cancel record);

    int updateByPrimaryKey(Cancel record);
}