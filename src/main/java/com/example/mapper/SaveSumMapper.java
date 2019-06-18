package com.example.mapper;


import com.example.entity.Single;
import com.example.entity.Units;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SaveSumMapper {


    //将新导入的项目的单项工程总费用保存
    int insertSingle(@Param("list") List<Single> list);

    //按照项目名称查询单项工程汇总数据
    List<Single> selectSingle(@Param("maplnfo_name") String maplnfo_name);

    //新导入的项目的单位工程总费用保存
    int insertUnits(@Param("list") List<Units> list);

    //按照项目名称以及单项工程名称获取单位汇总数据
    List<Units> selectUnits(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);
}
