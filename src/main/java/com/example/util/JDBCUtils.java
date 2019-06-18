package com.example.util;

import com.example.entity.Total;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC连接的工具类
 */
public class JDBCUtils {

    //初始化连接
    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;

    //设置连接的sql语句，端口，访问端口
    private static final String SQL_STAYEMENT = "select sum(amount*integrated_unit_price) as k,maplnfo_name,single_name,unit_engineering from b group by maplnfo_name,single_name,unit_engineering;";
    private static final String IMPALA_HOST = "192.168.100.11";
    private static final String IMPALA_JDBC_PORT = "21050";
    //设置连接地址
    private static final String CONNECTION_URL = "jdbc:hive2://" + IMPALA_HOST + ":" + IMPALA_JDBC_PORT + "/cost_data;auth=noSasl";
    //使用的包
    private static final String JDBC_DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

    /**
     * 连接数据库
     */
    public static Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER_NAME);
            connection = DriverManager.getConnection(CONNECTION_URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭资源
     */
    public static void close() {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
            if (preparedStatement != null) {
                preparedStatement.close();
                preparedStatement = null;
            }
            if (resultSet != null) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将结果集转换成实体对象集合
     * @param rs 结果集
     * @param cc 实体对象映射类
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static List Populate(ResultSet rs, Class cc) throws SQLException, InstantiationException, IllegalAccessException{

        //结果集 中列的名称和类型的信息
        ResultSetMetaData rsm = rs.getMetaData();
        int colNumber = rsm.getColumnCount();
        List list = new ArrayList();
        Field[] fields = cc.getDeclaredFields();

        //遍历每条记录
        while(rs.next()){
            //实例化对象
            Object obj = cc.newInstance();
            //取出每一个字段进行赋值
            for(int i=1;i<=colNumber;i++){
                Object value = rs.getObject(i);
                //匹配实体类中对应的属性
                for(int j = 0;j<fields.length;j++){
                    Field f = fields[j];
                    if(f.getName().equals(rsm.getColumnName(i))){
                        boolean flag = f.isAccessible();
                        f.setAccessible(true);
                        f.set(obj, value);
                        f.setAccessible(flag);
                        break;
                    }
                }

            }
            list.add(obj);
        }
        return list;
    }

    /**
     * 工具实施
     * @param sql
     * @param cc
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static List ImpalaUtils(String sql, Class cc) throws SQLException, IllegalAccessException, InstantiationException {

        Connection conn = JDBCUtils.getConnection();      //JDBCUtils 自己定义的一个类
        PreparedStatement pre = null;
        ResultSet res = null;
        pre = conn.prepareStatement(sql);
        res = pre.executeQuery();
        //调用将结果集转换成实体对象方法
        List list = JDBCUtils.Populate(res, cc);
        return list;
    }





}









