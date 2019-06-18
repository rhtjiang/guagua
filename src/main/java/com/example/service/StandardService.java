package com.example.service;

import com.example.entity.StandardTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StandardService {




    //通过清单项查询质量表
    List<StandardTable> findStandard(@Param("project_name") String project_name);
}
