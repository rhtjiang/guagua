package com.example.service;

import com.example.entity.Partial;
import com.example.entity.Total;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartialService {

    //查询分部分项表数据
    List<Partial> findPartial(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering, @Param("partial_name") String partial_name);

    //按照给定的方式分类汇总
    List<Partial> findAllPars(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering, @Param("partial_name") String partial_name);

    //对比模板和新导入项目的分部分项数据查看是否有漏项行为，相似项目中暂时以项目名称作为依据
    List<Partial> anPartial(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("m_name") String m_name);

    //对比模板和新导入项目的分部分项数据查看是否有多项行为，相似项目中暂时以项目名称作为依据
    List<Partial> anPartials(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("m_name") String m_name);

    //通过去重查询获取标准项目中建设项目名称
    List<Partial> findProjectName();

    //通过去重查询获取实例中建设项目名称
    List<Partial> findProjectNames();

    //通过去重查询指定的单项工程名称
    List<Partial> findSiName(@Param("maplnfo_name") String maplnfo_name);

    //通过去重获取模板项目
    List<Partial> findStand();

    //将漏项的数据存入实例项目中
    boolean load(@Param("p") Partial p);

    //将多项中清单项进行清除
    boolean removePa(@Param("uid") String uid);

    //查询实例表中数据
    List<Partial> findPartial2(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering, @Param("partial_name") String partial_name);

    //将实例项目归档到模板中
    boolean loadStand(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("m_name") String m_name);

    //查询指定模板库中数据
    List<Partial> findStandPa(@Param("m_name") String m_name);

    //将实例项目中指定数据存入到标准项目中
    boolean loadP2ToP1(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name);

    //多条件搜索标准项目的数据信息
    List<Partial> finMCD(@Param("maplnfo_name") String maplnfo_name, @Param("single_name") String single_name, @Param("unit_engineering") String unit_engineering, @Param("searchs") String searchs);

    //查询每一个清单项的标签
    List<Partial> findPaLabel(@Param("maplnfo_name") String maplnfo_name);
}
