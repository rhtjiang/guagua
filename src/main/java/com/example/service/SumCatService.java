package com.example.service;

import com.example.entity.SumCats;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SumCatService {




    /**
     *
     * @param
     * @return
     */
    List<SumCats> findSumM(@Param("id") Integer id);


    /**
     * 修改
     *
     * @return
     */
    public boolean modifyK(SumCats sumCats);


    /**
     * 删除
     *
     * @return
     */
    public boolean moveK(@Param("id") Integer id);

    /**
     * zengjia
     *
     * @return
     */
    public boolean addK(SumCats sumCats);

}
