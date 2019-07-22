package com.javan.dao;

import com.javan.entity.Cloth;
import com.javan.entity.ClothExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ClothMapper {
    int countByExample(ClothExample example);

    int deleteByExample(ClothExample example);

    int deleteByPrimaryKey(Integer cloth_id);

    int insert(Cloth record);

    int insertSelective(Cloth record);

    List<Cloth> selectByExample(ClothExample example);

    Cloth selectByPrimaryKey(Integer cloth_id);

    int updateByExampleSelective(@Param("record") Cloth record, @Param("example") ClothExample example);

    int updateByExample(@Param("record") Cloth record, @Param("example") ClothExample example);

    int updateByPrimaryKeySelective(Cloth record);

    int updateByPrimaryKey(Cloth record);
}