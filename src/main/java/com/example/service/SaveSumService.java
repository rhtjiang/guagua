package com.example.service;

import com.example.entity.Single;
import com.example.entity.Units;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaveSumService {


    //将新导入的项目的单项工程总费用保存
    boolean loadSingle(@Param("list") List<Single> list);

    //按照项目名称查询单项工程汇总数据
    List<Single> findSingle(@Param("maplnfo_name") String maplnfo_name);

    //新导入的项目的单位工程总费用保存
    boolean loadUnits(@Param("list") List<Units> list);

    //按照项目名称以及单项工程名称获取单位汇总数据
    List<Units> findUnits(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);
}
