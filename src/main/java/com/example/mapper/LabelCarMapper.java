package com.example.mapper;

import com.example.entity.LabelCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LabelCarMapper {



    //将某个项目的清单标签项存入仓库
   void insertLabelCar(@Param("list") List<LabelCar> list);



}
