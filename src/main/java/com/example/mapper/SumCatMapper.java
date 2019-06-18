package com.example.mapper;

import com.example.entity.SumCats;
import com.example.pojo.SumCat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SumCatMapper {

    /**
     *
     * @param
     * @return
     */
   List<SumCats> selectSumM(@Param("id") Integer id);


    /**
     * 修改
     * @param sumCats
     * @return
     */
   int updateK(SumCats sumCats);

    /***
     * 删除
     * @param
     * @return
     */
   int deleteK(@Param("id") Integer id);

    /**
     * 增加
     * @param sumCats
     * @return
     */
   int insertK(SumCats sumCats);

}
