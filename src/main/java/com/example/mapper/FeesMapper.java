package com.example.mapper;

import com.example.entity.Fees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FeesMapper {

    //查规费税金表
    List<Fees> selectFees(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //按需进行汇总
    List<Fees> selectAllFees(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //对规费进行统计
    List<Fees> selectFe(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);

    //将实例项目中指定数据存入到标准项目中
    void insertF2ToF2(@Param("maplnfo_name")String maplnfo_name, @Param("single_name") String single_name);
}
