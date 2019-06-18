package com.example.service;

import com.example.entity.Approval;
import com.example.entity.Record;
import com.example.entity.list_dic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SelectTableService {



    /**
     * 通过项目名称，单项工程名称，单位工程名称，分部分项名称，清单项名称来获取每一个清单项具体需要的表格
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @param partial_name
     * @param project_name
     * @return
     */
    List<list_dic> findTablePar(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering, @Param("partial_name") String partial_name, @Param("project_name") String project_name);


    /**
     * 将数据存入到回填土施工记录表
     * @param record
     * @return
     */
    boolean loadRecord(@Param("r") Record record);

    /**
     * //修改回填土清单项中，回填土施工记录表的状态为已完成
     * @param item_id
     * @param dic_id
     * @return
     */
   boolean modifyRecordStatus(@Param("item_id") Integer item_id, @Param("dic_id") Integer dic_id);

    /**
     * 修改回填土清单项中，回填土施工记录表下一级的状态为需填
     * @param item_id
     * @param
     * @return
     */
    boolean modifyRelateStatus(@Param("item_id") Integer item_id, @Param("relate_id") Integer relate_id);

    /**
     * //查询地基表中数据回填到验证表中
     * @param item_id
     * @return
     */
    List<Record> findRecord(@Param("item_id") Integer item_id);


    /**
     * 将数据存入到回填土施工审批表
     * @param
     * @return
     */
    boolean loadApproval(@Param("a") Approval approval);

    /**
     * //获取每张记录表的下一级关联表
     * @param dic_id
     * @return
     */
    List<Integer> findT_relate(@Param("dic_id") Integer dic_id);


    /**
     * 获取该等级的下一等级的表数据
     * @param dic_id
     * @return
     */
    List<list_dic>  findT(@Param("dic_id") Integer dic_id);
}
