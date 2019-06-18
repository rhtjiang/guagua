package com.example.mapper;

import com.example.entity.StandardTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StandardMapper {


    //通过清单项查询质量表
    List<StandardTable> selectStandard(@Param("project_name") String project_name);


}
