package com.example.service;

import com.example.entity.Unit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UnitService {

    //查单价措施表
    List<Unit> findUnit(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering,@Param("partial_name") String partial_name);

    //按需求分类汇总
    List<Unit> findAllUnits(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering,@Param("partial_name") String partial_name);

    //获取建筑面积
    List<Unit> findAreas(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);

    //将实例项目中指定数据存入到标准项目中
    boolean loadU2ToU1(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);

}
