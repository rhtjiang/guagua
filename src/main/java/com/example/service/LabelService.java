package com.example.service;

import com.example.entity.Label;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LabelService {

    //标签查询部分
    List<Label> findLabel(@Param("major") String major,@Param("name") String name,@Param("field") String field);

    //增加标签项
    public void addLabel(Label label);

    //修改标签
    public void modifyLabel(Label label);



}
