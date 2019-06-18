package com.example.util;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

//第一个文件格式的产生以及改变
final class MyFileListener implements FileAlterationListener {
    /**
     * 监控开始
     *
     * @param fileAlterationObserver
     */
    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {
        System.out.println("monitor start scan files..");
    }

    /**
     * 监控文件夹的产生
     *
     * @param file
     */
    @Override
    public void onDirectoryCreate(File file) {

        System.out.println(file.getName() + " director created.");
    }

    /**
     * 监控文件夹的改变
     *
     * @param file
     */
    @Override
    public void onDirectoryChange(File file) {
        System.out.println(file.getName() + " director changed.");
    }

    /**
     * 监控文件夹的删除
     *
     * @param file
     */
    @Override
    public void onDirectoryDelete(File file) {
        System.out.println(file.getName() + " director deleted.");
    }

    /**
     * 监控文件的产生
     *
     * @param file
     */
    @Override
    public void onFileCreate(File file) {
        //定义分部分项的清单表
        String partia3 = "分部分项工程清单计价表";
        //定义单价措施项目清单表
        String unit3 = "单价措施项目清单计价表";
        //定义总价措施
        String all3 = "总价措施";
        //定义规费税金
        String fees3 = "规费";
        //材料和工程设备
        String mer3 = "材料和工程设备";
        //判断是否生成分部分项表如果有，则执行kettle清洗数据
        if (file.getName() != null && file.getName().contains(partia3)) {
            KettleUtil kettleUtil = new KettleUtil();
         //   kettleUtil.kettleCleanOut(file.getName(), "partial3","民用");
        } /*else if (file.getName() != null && file.getName().contains(unit3)) {
            KettleUtil kettleUtil = new KettleUtil();
            kettleUtil.kettleCleanOut(file.getName(), "unit3");
        } else if (file.getName() != null && file.getName().contains(all3)) {
            KettleUtil kettleUtil = new KettleUtil();
            kettleUtil.kettleCleanOut(file.getName(), "all3");
        } else if (file.getName() != null && file.getName().contains(fees3)) {
            KettleUtil kettleUtil = new KettleUtil();
            kettleUtil.kettleCleanOut(file.getName(), "fees3");
        } else if (file.getName() != null && file.getName().contains(mer3)) {
            KettleUtil kettleUtil = new KettleUtil();
            kettleUtil.kettleCleanOut(file.getName(), "mer3");
        } else if (file.getName() == null) {
            System.out.println("文件正在解析");
        }*/
    }

    /**
     * 监控文件的改变
     *
     * @param file
     */
    @Override
    public void onFileChange(File file) {
        System.out.println(file.getName() + " changed.");
    }

    /**
     * 监控文件的删除
     *
     * @param file
     */
    @Override
    public void onFileDelete(File file) {
        System.out.println(file.getName() + " deleted.");
    }

    /**
     * 停止监控
     *
     * @param fileAlterationObserver
     */
    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
        System.out.println("monitor stop scanning..");
    }


}
