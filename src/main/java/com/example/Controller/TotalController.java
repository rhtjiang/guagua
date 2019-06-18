package com.example.Controller;

import com.example.entity.Total;
import com.example.service.TotalService;
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
public class TotalController {


    //初始化service
    @Autowired
    private TotalService totalService;


    /**
     * 查总价措施表
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     */
    @RequestMapping("/selectTo")
    @ResponseBody
    public List selectTotal(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String unit_engineering) {
        List<Total> total = totalService.findTotal(maplnfo_name, single_name, unit_engineering);
        return total;

    }

    /**
     * 按需分类汇总
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     */
    @RequestMapping("/selectAllTo")
    @ResponseBody
    public List selectAllTotal(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String unit_engineering) {
        List<Total> allTotal = totalService.findAllTotals(maplnfo_name, single_name, unit_engineering);
        return allTotal;

    }

    /**
     * 分组查询安全文明费
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     */
    @RequestMapping("/selectSa")
    @ResponseBody
    public List selectSalf(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String unit_engineering) {
        List<Total> salf = totalService.findSalf(maplnfo_name, single_name, unit_engineering);
        return salf;

    }

    /**
     * 查询建设项目名称
     *
     * @return
     */
    @RequestMapping("/selectName")
    @ResponseBody
    public List selectName() {
        List<Total> projectName = totalService.findProjectName();
        List list = new ArrayList();
        for (Total s : projectName) {
            String maplnfo_name = s.getMaplnfo_name();
            list.add(maplnfo_name);

        }
        return list;
    }


    /**
     * 通过项目名称，单项工程名称，以及单位工程名称获取总价措施明细表
     */
    @RequestMapping("/table/all")
    @ResponseBody
    public List selectAll(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering) {

        List<Total> total = totalService.findTotal(maplnfo_name, single_name, unit_engineering);
        List list = new ArrayList<String>();
        List list1 = new ArrayList<String>();
        Map map1 = new HashMap<>();
        int i = 1;
        int j = 2;
        for (Total t : total) {
            Map map = new HashMap<>();
            String bop = t.getBop();
            String project_name = t.getProject_name();
            Double rate = t.getRate();
            Double money = t.getMoney();
            if (bop.contains("-")) {
                //安全文明施工费的子集
                map.put("id", i++);
                map.put("fid", 1);
                map.put("bop", bop);
                map.put("project_name", project_name);
                map.put("rate", rate);
                map.put("money", money);
                list.add(map);
            } else if (project_name.equals("安全文明施工")) {
                //安全文明施工费
                map.put("id", 1);
                map.put("fid", -1);
                map1.put("bop", bop);
                map1.put("project_name", project_name);
                map1.put("rate", rate);
                map1.put("money", money);
                map1.put("nodes", list);
                list1.add(map1);
            } else {
                //其他费用
                Map map2 = new HashMap();
                map.put("id", j++);
                map.put("fid", -1);
                map2.put("bop", bop);
                map2.put("project_name", project_name);
                map2.put("rate", rate);
                map2.put("money", money);
                list1.add(map2);


            }
        }

        return list1;
    }


}
