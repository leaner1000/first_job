package com.javan.dao;

import com.javan.entity.Outstockinfo;
import com.javan.entity.OutstockinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutstockinfoMapper {
    int countByExample(OutstockinfoExample example);

    int deleteByExample(OutstockinfoExample example);

    int deleteByPrimaryKey(Integer out_stock_id);

    int insert(Outstockinfo record);

    int insertSelective(Outstockinfo record);

    List<Outstockinfo> selectByExample(OutstockinfoExample example);

    Outstockinfo selectByPrimaryKey(Integer out_stock_id);

    int updateByExampleSelective(@Param("record") Outstockinfo record, @Param("example") OutstockinfoExample example);

    int updateByExample(@Param("record") Outstockinfo record, @Param("example") OutstockinfoExample example);

    int updateByPrimaryKeySelective(Outstockinfo record);

    int updateByPrimaryKey(Outstockinfo record);
}