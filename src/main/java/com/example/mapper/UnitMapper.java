package com.example.mapper;

import com.example.entity.Unit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UnitMapper {

    //查单价措施表
    List<Unit> selectUnit(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering,@Param("partial_name") String partial_name);

    //按需求分类汇总
    List<Unit> selectAllUnits(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering,@Param("partial_name") String partial_name);

    //获取建筑面积
    List<Unit> selectAreas(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);

    //将实例项目中指定数据存入到标准项目中
    void insertU2ToU2(@Param("maplnfo_name")String maplnfo_name, @Param("single_name") String single_name);
}
