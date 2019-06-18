package com.example.service;

import com.example.entity.Fees;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeesService {


    //查规费税金表
    List<Fees> findFees(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //按需进行汇总
    List<Fees> findAllFees(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //对规费进行统计
    List<Fees> findFe(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //将实例项目中指定数据存入到标准项目中
    boolean loadF2ToF1(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);
}
