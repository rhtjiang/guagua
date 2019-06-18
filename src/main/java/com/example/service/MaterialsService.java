package com.example.service;

import com.example.entity.Materials;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialsService {


    //查材料表
    List<Materials> findMaterials(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering);


    //将实例项目中指定数据存入到标准项目中
    boolean loadM2ToM1(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);

}
