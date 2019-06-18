package com.example.service;

import com.example.entity.Total;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TotalService {

    //查总价措施表
    List<Total> findTotal(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //按需汇总
    List<Total> findAllTotals(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //分组查询安全文明费
    List<Total> findSalf(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //通过去重查询获取建设项目名称
    List<Total> findProjectName();

    //将实例项目中指定数据存入到标准项目中
    boolean loadT2ToT1(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);
}
