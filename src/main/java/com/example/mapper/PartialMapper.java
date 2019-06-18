package com.example.mapper;

import com.example.entity.Partial;
import com.example.entity.Total;
import org.apache.hadoop.hive.metastore.model.MPartition;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface PartialMapper {

    //查询分部分项表数据
    List<Partial> selectPartial(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name,@Param("unit_engineering") String unit_engineering,@Param("partial_name") String partial_name);

    //按照给出的方式，分类汇总
    List<Partial> selectAllPars(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name,@Param("unit_engineering") String unit_engineering,@Param("partial_name") String partial_name);




    //对比模板和新导入项目的分部分项数据查看是否有漏项行为，相似项目中暂时以项目名称作为依据
    List<Partial> analPar(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name,@Param("m_name") String m_name);
    //对比模板和新导入项目的分部分项数据查看是否有多项行为，相似项目中暂时以项目名称作为依据
    List<Partial> analpars(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name,@Param("m_name") String m_name);

    //通过去重查询获取标准项目中建设项目名称
    List<Partial> selectProjectName();

    //通过去重查询获取实例中建设项目名称
    List<Partial> selectProjectNames();

    //通过去重查询指定的单项工程名称
    List<Partial> selectSiName(@Param("maplnfo_name") String maplnfo_name);

    //通过去重获取模板库数据名称
    List<Partial> selectStand();


    //将漏项的数据存入实例项目中

    void insertPa(@Param("p") Partial p);

    //对于多项的清单进行删除
    void deletePa(@Param("uid") String uid);

    //查询实例表中数据
    List<Partial> selectPartial2(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name,@Param("unit_engineering") String unit_engineering,@Param("partial_name") String partial_name);


    //将实例项目归档到模板中
    void insertStand(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name,@Param("m_name") String m_name);

    //查询指定模板中数据
    List<Partial> selectStandPa(@Param("m_name") String m_name);

    //将实例项目中指定数据存入到标准项目中
    void insertP2ToP2(@Param("maplnfo_name")String maplnfo_name, @Param("single_name") String single_name);

    //多条件搜索标准项目的数据信息
    List<Partial> searchMCD(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name,@Param("unit_engineering") String unit_engineering,@Param("searchs")String searchs);


    //查询每一个清单项的标签
    List<Partial> selectPaLabel(@Param("maplnfo_name") String maplnfo_name);


}
