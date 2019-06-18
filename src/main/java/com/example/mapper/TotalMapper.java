package com.example.mapper;

import com.example.entity.Total;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TotalMapper {

    //查总价措施表
    List<Total> selectTotal(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //按需汇总
    List<Total> selectAllTotals(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //分组查询安全文明费
    List<Total> selectSalf(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //通过去重查询获取建设项目名称
    List<Total> selectProjectName();


    //将实例项目中指定数据存入到标准项目中
    void insertT2ToT2(@Param("maplnfo_name")String maplnfo_name, @Param("single_name") String single_name);

}
