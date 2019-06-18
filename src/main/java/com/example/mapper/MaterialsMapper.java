package com.example.mapper;

import com.example.entity.Materials;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MaterialsMapper {

    //查材料表
    List<Materials> selectMaterials(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name,@Param("unit_engineering") String unit_engineering);



    //将实例项目中指定数据存入到标准项目中
    void insertM2ToM2(@Param("maplnfo_name")String maplnfo_name, @Param("single_name") String single_name);
}
