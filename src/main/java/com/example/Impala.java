package com.example;

import com.example.entity.*;
import com.example.util.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Impala {

    public static void main(String[] args) {
        TotalAll();
    }


    /**
     * 查询分部分项表数据
     */
    public static void Partial() {
        String sql = "select bop,project_name,signalment.item as signalment,prickle,amount,integrated_unit_price,maplnfo_name,single_name,unit_engineering,partial_name from bid_partial,bid_partial.signalment as signalment";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Partial.class);
            //将对象循环获取出来
            for (int i = 0; i < list.size(); i++) {
                Partial partial = (Partial) list.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分部分项表按照建设项目，单项工程分类汇总
     */
    public static void Partials() {
        String sql = "select sum(amount*integrated_unit_price) as amount,maplnfo_name,single_name,unit_engineering from bid_partial group by maplnfo_name,single_name,unit_engineering";

        try {
            List list = JDBCUtils.ImpalaUtils(sql, Partial.class);
            for (int i = 0; i < list.size(); i++) {
                Partial partial = (Partial) list.get(i); }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查单价措施表
     */
    public static void Unit() {
        String sql = "select bop,project_name,signalment.item as signalment,prickle,amount,integrated_unit_price,maplnfo_name,single_name,unit_engineering,partial_name from bid_unit,bid_unit.signalment as signalment";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Unit.class);
            //将对象循环获取出来
            for (int i = 0; i < list.size(); i++) {
                Unit unit = (Unit) list.get(i);
                System.out.println("[Bop = " + unit.getBop() + ",project_name=" + unit.getProject_name() + ",signalment=" + unit.getSignalment() + ",prickle=" + unit.getPrickle() + ",amount=" + unit.getAmount() + ",integrated_unit_price=" + unit.getIntegrated_unit_price() + ",single_name=" + unit.getSingle_name() + ",passwd = " + unit.getMaplnfo_name() + ",unit_engineering=" + unit.getUnit_engineering() + ",partial_name=" + unit.getPartial_name() + "]");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单价措施表按照建设项目，单项工程，专业进行汇总
     */
    public static void Units() {
        String sql = "select sum(amount*integrated_unit_price) as amount,maplnfo_name,single_name,unit_engineering from bid_unit group by maplnfo_name,single_name,unit_engineering";

        try {
            List list = JDBCUtils.ImpalaUtils(sql, Partial.class);
            for (int i = 0; i < list.size(); i++) {
                Partial partial = (Partial) list.get(i);
                System.out.println(partial.getAmount() + partial.getMaplnfo_name() + partial.getSingle_name() + partial.getUnit_engineering() + partial.getPartial_name());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查总价措施表
     */
    public static void Toals() {
        String sql = "select * from bid_all";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Total.class);
            //将对象循环获取出来
            for (int i = 0; i < list.size(); i++) {
                Total total = (Total) list.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将总价措施表按照建设项目，单项工程，专业进行汇总
     */
    public static void TotalAll() {
        String sql = "select maplnfo_name,single_name,unit_engineering,sum(money*1) money from bid_all where project_name != '安全文明施工' group by maplnfo_name,single_name,unit_engineering";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Total.class);
            //将对象循环获取出来
            for (int i = 0; i < list.size(); i++) {
                Total total = (Total) list.get(i);
                System.out.println(total.getMaplnfo_name() + total.getSingle_name() + total.getUnit_engineering() + total.getMoney());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分组查询安全文明费
     */
    public static void salfl() {
        String sql = "select maplnfo_name,single_name,unit_engineering,money from cost_data.bid_all where project_name = '安全文明施工'";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Total.class);
            //将对象循环获取出来
            for (int i = 0; i < list.size(); i++) {
                Total total = (Total) list.get(i);
                System.out.println(total.getMaplnfo_name() + total.getSingle_name() + total.getUnit_engineering() + total.getMoney());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }




    /**
     * 查规费税金表
     */
    public static void Fee() {
        String sql = "select * from bid_fees";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Fees.class);
            //将对象循环获取出来
            for (int i = 0; i < list.size(); i++) {
                Fees fees = (Fees) list.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将规费税金表，按照建设项目，单项工程，专业进行汇总
     */
    public static void taxeSum() {
        String sql = "select maplnfo_name,single_name,unit_engineering,sum(base*rate*0.01) money from cost_data.bid_fees group by maplnfo_name,single_name,unit_engineering";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Fees.class);
            for (int i = 0; i < list.size(); i++) {
                Fees fees = (Fees) list.get(i);
                System.out.println(fees.getMaplnfo_name()+fees.getSingle_name()+fees.getUnit_engineering()+fees.getMoney());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对规费进行统计
     */
    public static void feesSum() {
        String sql="select  maplnfo_name,single_name,unit_engineering,sum(base*rate*0.01) money from bid_fees where project_name !='销项增值税额' group by maplnfo_name,single_name,unit_engineering";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Fees.class);
            for (int i = 0; i < list.size(); i++) {
                Fees fees = (Fees) list.get(i);
                System.out.println(fees.getMaplnfo_name()+fees.getSingle_name()+fees.getUnit_engineering()+fees.getMoney());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查材料表
     */
    public static void Mater() {
        String sql = "select * from bid_materials";
        try {
            List list = JDBCUtils.ImpalaUtils(sql, Materials.class);
            //将对象循环获取出来
            for (int i = 0; i < list.size(); i++) {
                Materials ma = (Materials) list.get(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获得各个单项工程的建筑面积
     */
    public static void areas() throws IllegalAccessException, SQLException, InstantiationException {
        String sql = "select maplnfo_name,single_name,max(case when project_name='综合脚手架' then amount else 1 end ) as amount from bid_unit where unit_engineering='建筑与装饰工程' and partial_name='脚手架工程' group by maplnfo_name,single_name";
        List list = JDBCUtils.ImpalaUtils(sql, Unit.class);

        for (int i = 0; i < list.size(); i++) {
            Unit unit = (Unit) list.get(i);
            System.out.println(unit.getAmount() + "," + unit.getMaplnfo_name() + "," + unit.getSingle_name());
        }
    }
}
