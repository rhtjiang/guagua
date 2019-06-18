package com.example.util;

import org.pentaho.di.core.KettleEnvironment;
import org.pentaho.di.core.Result;
import org.pentaho.di.core.RowMetaAndData;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.job.Job;
import org.pentaho.di.job.JobMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KettleUtil {
    /**
     * 没有参数是,设置参数为null
     *
     * @param paras
     */
    public String runTransformation(Map<String, String> paras, String kjbName) {
        //导入的项目名称
        String string = null;
        //kettle初始化
        try {
            //初始化kettle环境
            KettleEnvironment.init();
            //获得job的路径及名
            //   JobMeta jobMeta = new JobMeta("C:\\Users\\Lee\\Desktop\\kettle作业\\分部分项工程清单计价表.kjb",null);
            JobMeta jobMeta = new JobMeta("C:\\Users\\Lee\\Desktop\\kettle作业\\" + kjbName + ".kjb", null);
            // JobMeta jobMeta = new JobMeta("C:\\Users\\Lee\\Desktop\\kettle作业\\" + "partial3" + ".kjb", null);
            //加入job容器
            Job job = new Job(null, jobMeta);
            //传入参数
            for (Map.Entry<String, String> entry : paras.entrySet()) {
                //向ktr中传值
                job.setVariable(entry.getKey(), entry.getValue());

            }
            //开始作业
            job.start();
            //等待作业完成
            job.waitUntilFinished();
            if (kjbName.equals("partial3")) {
                Result result = job.getResult();
                List<RowMetaAndData> rows = result.getRows();
                RowMetaAndData rowMetaAndData = rows.get(0);
                string = rowMetaAndData.getString(0, "MN");
            }

        } catch (KettleException e) {
            e.printStackTrace();
        }
        return string;
    }

    public String kettleCleanOut(String filename, String kjbName) {
        String fileUrl = "D:\\ok\\" + filename;
        //调用job
        KettleUtil etl = new KettleUtil();
        //当有多个参数时，把参数封装成一个map集合
        Map<String, String> paras = new HashMap<String, String>();
        //给转换中命名参数赋值，这里将需要解析的excel文件路径fileUrl传到ktr中
        paras.put("FILE_PATH", fileUrl);
        paras.put("build_type", "'" + "民用" + "'");
        String partial3 = etl.runTransformation(paras, "partial3");
        return partial3;
    }
}
