package com.example.util;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Monitor {


    public static void main(String[] args) throws Exception {
        //监控的文件夹路径
        //File directory = new File("/bigdata/monitor/");
        File directory = new File("D:\\ok\\");
        // 轮询间隔 1秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        // 创建一个文件观察器用于处理文件的格式
        //过滤文件的格式，当文件后缀是.xls时监控该文件
        FileAlterationObserver observer = new FileAlterationObserver(directory, FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(), FileFilterUtils.suffixFileFilter(".xls")));
        //设置文件变化监听器
        observer.addListener(new MyFileListener());
        //设置监听
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        System.out.println(monitor);
        monitor.start();
    }
}


