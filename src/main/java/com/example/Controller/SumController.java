package com.example.Controller;

import com.example.entity.*;
import com.example.pojo.SumCat;
import com.example.service.*;
import com.example.util.DecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class SumController {

    //初始化Service
    //分部分项表
    @Autowired
    private PartialService partialService;
    //单价措施表
    @Autowired
    private UnitService unitService;
    //总价措施表
    @Autowired
    private TotalService totalService;
    //规费税金表
    @Autowired
    private FeesService feesService;
    @Autowired
    private SumCatService sumCatService;
    @Autowired
    private SaveSumService saveSumService;

    /**
     * 通过项目名称，按照单项工程汇总
     *
     * @param maplnfo_name
     * @return
     */
    @RequestMapping("/sum")
    @ResponseBody
    public List Sum(@RequestParam String maplnfo_name) {
        List<Single> single = saveSumService.findSingle(maplnfo_name);
        return single;
    }


    //******************************用于总额数据存储接口，之后删除**********************************************//
   /* @RequestMapping("/saveSingle")
    @ResponseBody
    public boolean SaveSingle(@RequestParam String maplnfo_name) {
        SumCat sumCat = new SumCat();
        List<Map<String, Object>> result = sumCat.AllCat(maplnfo_name, partialService, unitService, totalService, feesService);
        List<Single> list = new ArrayList<>();
        for (Map m : result) {
            String single_name = (String) m.get("single_name");
            double money = (double) m.get("money");
            double money1 = (double) m.get("money1");
            double money2 = (double) m.get("money2");
            Single single = new Single();
            single.setMaplnfo_name(maplnfo_name);
            single.setSingle_name(single_name);
            single.setSum_money(money);
            single.setSalfe_money(money2);
            single.setFees_money(money1);
            list.add(single);
        }
        boolean b = saveSumService.loadSingle(list);
        return b;
    }


    //按照单位工程汇总存储
    @RequestMapping("/saveUnits")
    @ResponseBody
    public boolean sssss(@RequestParam String maplnfo_name) {
        SumCat sumCat = new SumCat();
        List<List<Map<String, Object>>> lists = sumCat.AllCatFs(maplnfo_name, partialService, unitService, totalService, feesService);
        List<Units> list = new ArrayList<>();
        for (List<Map<String, Object>> maps:lists) {
            for (Map ms : maps) {
                maplnfo_name = (String) ms.get("maplnfo_name");
                String single_name = (String) ms.get("single_name");
                String unit_engineering = (String) ms.get("unit_engineering");
                double moneys = (double) ms.get("moneys");
                double money1 = (double) ms.get("money1");
                double money2 = (double) ms.get("money2");
                Units units = new Units();
                units.setMaplnfo_name(maplnfo_name);
                units.setSingle_name(single_name);
                units.setUnit_engineering(unit_engineering);
                units.setSum_money(moneys);
                units.setSalfe_money(money2);
                units.setFees_money(money1);
                list.add(units);

            }
        }
        boolean b = saveSumService.loadUnits(list);
        return b;
    }*/
    //*****************************************************************************************************//

    /**
     * 通过点击单项工程，按照单位工程分组获取汇总求和
     *
     * @param maplnfo_name
     * @param single_name
     * @return
     */
    @RequestMapping("/sumf")
    @ResponseBody
    public List SumF(@RequestParam String maplnfo_name, @RequestParam String single_name) {
        List<Units> units = saveSumService.findUnits(maplnfo_name, single_name);
      //  SumCat sumCat = new SumCat();
      //  List<Map<String, Object>> result = sumCat.AllCatF(maplnfo_name, single_name, partialService, unitService, totalService, feesService);
        System.out.println(units+"---");
        return units;
    }

    /**
     * 工程造价构成指标
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @param partial_name
     * @param money
     * @return
     */
    @RequestMapping("/apars")
    @ResponseBody
    public List analyPars(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering, @RequestParam(value = "partial_name", required = false) String partial_name, @RequestParam(value = "money", required = false) double money) {
        List list = new ArrayList();
        //单项工程的总造价money
        //单项工程的面积areas
        ///double areas = unitService.findAreas(maplnfo_name, single_name);
        List<Unit> areas1 = unitService.findAreas(maplnfo_name, single_name);
        double amount1 = areas1.get(0).getAmount();
        double areas = amount1;
        //各个分部分项工程的汇总数据
        List<Partial> allPars = partialService.findAllPars(maplnfo_name, single_name, unit_engineering, partial_name);
        //措施项目的汇总数据
        List<Unit> allUnits = unitService.findAllUnits(maplnfo_name, single_name, unit_engineering, partial_name);
        //总价措施汇总数据
        List<Total> allTotals = totalService.findAllTotals(maplnfo_name, single_name, unit_engineering);
        //对规费税金进行统计
        List<Fees> fe = feesService.findAllFees(maplnfo_name, single_name, unit_engineering);
        //定义一个初始的分部分项的和
        double sumpartial = 0;
        //定义一个初始的单价措施的和
        double sumunit = 0;
        //定义一个初始的总价措施的和
        double sumtotal = 0;
        //定义一个初始的规费的和
        double sumfees = 0;
        List ll = new ArrayList();
        Map map = new HashMap();
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        Map map3 = new HashMap();
        //循环获取分部分项数据
        for (Partial p : allPars) {
            Map maps = new HashMap();
            maps.put("project", p.getUnit_engineering());
            maps.put("areas", DecimalUtils.round(p.getAmount(), areas, 2));
            maps.put("moneys", DecimalUtils.round(p.getAmount(), money, 2) * 100);
            ll.add(maps);
            sumpartial += p.getAmount();
        }
        //循环获取单价措施数据
        for (Unit u : allUnits) {
            sumunit += u.getAmount();
        }
        //循环获取总价措施数据
        for (Total t : allTotals) {
            sumtotal += t.getMoney();
        }
        //循环获取规费的数据
        for (Fees f : fe) {
            sumfees += f.getMoney();
        }
        map.put("project", "分部分项工程");
        map.put("areas", DecimalUtils.round(sumpartial, areas, 2));
        map.put("moneys", DecimalUtils.round(sumpartial, money, 2) * 100);
        map.put("nodes", ll);
        map1.put("project", "单价措施工程");
        map1.put("areas", DecimalUtils.round(sumunit, areas, 2));
        map1.put("moneys", DecimalUtils.round(sumunit, money, 2) * 100);
        map2.put("project", "总价措施工程");
        map2.put("areas", DecimalUtils.round(sumtotal, areas, 2));
        map2.put("moneys", DecimalUtils.round(sumtotal, money, 2) * 100);
        map3.put("project", "规费税金");
        map3.put("areas", DecimalUtils.round(sumfees, areas, 2));
        map3.put("moneys", DecimalUtils.round(sumfees, money, 2) * 100);
        list.add(map);
        list.add(map1);
        list.add(map2);
        list.add(map3);
        return list;
    }


    /**
     * //措施项目工程造价指标
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @param partial_name
     * @param money
     * @return
     */
    @RequestMapping("/fein")
    @ResponseBody
    public List feeIndex(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering, @RequestParam(value = "partial_name", required = false) String partial_name, @RequestParam(value = "money", required = false) double money) {
        List<Map> list = new ArrayList();
        //单项工程的总造价money
        //单项工程的面积areas
     //   double areas = unitService.findAreas(maplnfo_name, single_name);
        List<Unit> areas1 = unitService.findAreas(maplnfo_name, single_name);
        double amount1 = areas1.get(0).getAmount();
        double areas = amount1;
        //各个单价措施的汇总数据
        List<Unit> all = unitService.findAllUnits(maplnfo_name, single_name, unit_engineering, partial_name);
        List<Total> total = totalService.findTotal(maplnfo_name, single_name, unit_engineering);
        //循环获取单价措施项目
        for (Unit u : all) {
            Map map = new HashMap();
            map.put("ck", "措施项目（二）");
            map.put("project", u.getPartial_name());
            map.put("areas", DecimalUtils.round(u.getAmount(), areas, 2));
            map.put("moneys", DecimalUtils.mul(DecimalUtils.round(u.getAmount(), money, 4), 100, 2));
            list.add(map);
        }
        //循环获取总价措施
        for (Total t : total) {
            Map map1 = new HashMap();
            if (!t.getBop().contains("-")) {
                if (t.getMoney() != null) {
                    Double money1 = t.getMoney();
                    map1.put("ck", "措施项目（一）");
                    map1.put("project", t.getProject_name());
                    map1.put("areas", DecimalUtils.round(money1, areas, 2));
                    map1.put("moneys", DecimalUtils.mul(DecimalUtils.round(money1, money, 4), 100, 2));
                    list.add(map1);
                }
            }
        }


        //按照分部分项工程分类
        Map<Object, List> map1 = new HashMap();
        for (Map map : list) {
            String unit1 = (String) map.get("ck");
            if (map1.keySet().contains(unit1)) {
                map1.get(unit1).add(map);
            } else {
                List list1 = new ArrayList();
                list1.add(map);
                map1.put(unit1, list1);
            }
        }
        //循环遍历组装成nodes样式
        List list1 = new ArrayList();
        for (Map.Entry<Object, List> maps : map1.entrySet()) {
            String unity = maps.getKey().toString();
            Map map = new HashMap();
            map.put("project", unity);
            map.put("nodes", maps.getValue());
            list1.add(map);
        }


        return list1;

    }


    /**
     * @param
     * @return查询
     */
    @RequestMapping("/summ")
    @ResponseBody
    public List SumM(@RequestParam(value = "id", required = false) Integer id) {
        List<SumCats> sumM = sumCatService.findSumM(id);

        return sumM;
    }

    //修改
    @RequestMapping("/up")
    @ResponseBody
    public boolean up(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "name", required = false) String name) {
        SumCats sumCats = new SumCats();
        sumCats.setId(id);
        sumCats.setName(name);
        boolean b = sumCatService.modifyK(sumCats);
        return b;

    }

    //删除
    @RequestMapping("/de")
    @ResponseBody
    public boolean del(@RequestParam(value = "id", required = false) Integer id) {

        boolean b = sumCatService.moveK(id);
        return b;

    }

    //增加
    @RequestMapping("/ad")
    @ResponseBody
    public boolean ad(@RequestParam(value = "id", required = false) Integer id, @RequestParam(value = "name", required = false) String name) {
        SumCats sumCats = new SumCats();
        sumCats.setId(id);
        sumCats.setName(name);
        boolean b = sumCatService.addK(sumCats);
        return b;

    }
}
