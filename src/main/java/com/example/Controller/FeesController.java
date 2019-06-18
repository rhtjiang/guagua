package com.example.Controller;

import com.example.entity.Fees;
import com.example.service.FeesService;
import com.example.util.DecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FeesController {

    //初始化service
    @Autowired
    private FeesService feesService;


    /**
     * 查规费税金表
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     */
    @RequestMapping("/selectFe")
    @ResponseBody
    public List selectFee(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String unit_engineering) {
        List<Fees> fees = feesService.findFees(maplnfo_name, single_name, unit_engineering);
        return fees;

    }


    /**
     * 分类按需汇总
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     */
    @RequestMapping("/selectALLFe")
    @ResponseBody
    public List selectAllFee(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String unit_engineering) {
        List<Fees> allFees = feesService.findAllFees(maplnfo_name, single_name, unit_engineering);
        return allFees;
    }

    /**
     * 对规费进行统计
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     */
    @RequestMapping("/selectTaxe")
    @ResponseBody
    public List selectTaxe(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String unit_engineering) {
        List<Fees> fe = feesService.findFe(maplnfo_name, single_name, unit_engineering);
        return fe;
    }


    /**
     * 通过项目名称，单项工程名称，以及单位工程名称获取规费明细表
     */
    @RequestMapping("/table/fees")
    @ResponseBody
    public List selectFees(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering) {
        List<Fees> fees = feesService.findFees(maplnfo_name, single_name, unit_engineering);
        List list = new ArrayList<String>();
        List list2 = new ArrayList<String>();
        List list3 = new ArrayList<String>();
        String project_name = null;
        double mul = 0;
        double rate = 0;
        double base = 0;
        double world = 0;
        double sum = 0;
        int i = 1;
        for (Fees f : fees) {
            Map map = new HashMap<>();
            Map map3 = new HashMap<>();
            project_name = f.getProject_name();
            base = f.getBase();
            rate = f.getRate();

            mul = DecimalUtils.mul(rate * 0.01, base,2);
            if (!project_name.equals("销项增值税额")) {
                if (!project_name.equals("住房公积金")) {
                    //计算二级费用的综合
                    world += mul;
                    //将三级的费用放入map集合,并放入list
                    map.put("id", i++);
                    map.put("fid", 1);
                    map.put("project_name", project_name);
                    map.put("base", base);
                    map.put("rate", rate);
                    map.put("mul", mul);
                    list.add(map);
                } else {
                    //将住房公积金放入map3集合,并放入list2
                    map3.put("id", 2);
                    map3.put("fid", 1);
                    map3.put("project_name", project_name);
                    map3.put("base", base);
                    map3.put("rate", rate);
                    map3.put("mul", mul);
                    list2.add(map3);
                }
                sum += mul;
            } else {
                //将销项增值税放入map5并且放入list3
                Map map5 = new HashMap();
                map5.put("id", 2);
                map5.put("fid", -1);
                map5.put("project_name", project_name);
                map5.put("base", base);
                map5.put("rate", rate);
                map5.put("mul", mul);
                list3.add(map5);
            }
        }
        //社会保险费
        world = DecimalUtils.mul(world, 1,2);
        //规费
        sum = DecimalUtils.mul(sum, 1,2);
        //将社会保险费放入map2并且放入list2
        Map map2 = new HashMap();
        map2.put("fid", 1);
        map2.put("id", 1);
        map2.put("project_name", "社会保险费");
        map2.put("mul", world);
        map2.put("nodes", list);
        list2.add(map2);
        //将规费放入map4中 并且放入list3
        Map map4 = new HashMap();
        map4.put("id", 1);
        map4.put("fid", -1);
        map4.put("project_name", "规费");
        map4.put("mul", sum);
        map4.put("nodes", list2);
        list3.add(map4);

        System.out.println(list);
        sum = DecimalUtils.mul(sum, 1,2);
        return list3;


    }


}
