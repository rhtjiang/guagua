package com.example.mapper;

import com.example.entity.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface LabelMapper {
    //获取所有的标签
    List<Label> selectLabel(@Param("major") String major,@Param("name") String name,@Param("field") String field);

    //增加标签
    public void insertLabel(Label label);

    //修改标签
    public void updateLabel(Label label);
}
