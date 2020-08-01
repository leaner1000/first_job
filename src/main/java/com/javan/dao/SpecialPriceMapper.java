package com.javan.dao;

import com.javan.entity.SpecialPrice;
import com.javan.entity.SpecialPriceExample;
import com.javan.entity.SpecialPriceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpecialPriceMapper {
    int countByExample(SpecialPriceExample example);

    int deleteByExample(SpecialPriceExample example);

    int deleteByPrimaryKey(SpecialPriceKey key);

    int insert(SpecialPrice record);

    int insertSelective(SpecialPrice record);

    List<SpecialPrice> selectByExample(SpecialPriceExample example);

    SpecialPrice selectByPrimaryKey(SpecialPriceKey key);

    int updateByExampleSelective(@Param("record") SpecialPrice record, @Param("example") SpecialPriceExample example);

    int updateByExample(@Param("record") SpecialPrice record, @Param("example") SpecialPriceExample example);

    int updateByPrimaryKeySelective(SpecialPrice record);

    int updateByPrimaryKey(SpecialPrice record);
}