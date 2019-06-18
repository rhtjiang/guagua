package com.example.Controller;

import com.example.entity.Partial;
import com.example.entity.Unit;
import com.example.service.UnitService;
import com.example.util.DecimalUtils;
import com.example.util.RmoveUtils;
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
public class UnitController {

    //初始化service
    @Autowired
    private UnitService unitService;

    /**
     * 查单价措施表
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     */
    @RequestMapping("/selectUn")
    @ResponseBody
    public void selectUn(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String unit_engineering,@RequestParam(value = "partial_name",required = false) String partial_name){

        List<Unit> unit = unitService.findUnit(maplnfo_name,single_name,unit_engineering,partial_name);
    }

    /**
     * 分类汇总
     * @param maplnfo_name
     * @param single_name
     */
    @RequestMapping("/selectUns")
    @ResponseBody
    public void selectAllUn(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String unit_engineering,@RequestParam(value = "partial_name",required = false) String partial_name){
        List<Unit> allUnit = unitService.findAllUnits(maplnfo_name,single_name,unit_engineering,partial_name);
}


    /**
     * /查询单价措施表
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @return
     */
    @RequestMapping("/table/unit")
    @ResponseBody
    public List selectPar(@RequestParam(value = "maplnfo_name",required = false) String maplnfo_name, @RequestParam(value = "single_name",required = false) String single_name, @RequestParam(value = "unit_engineering",required = false) String unit_engineering,@RequestParam(value = "partial_name",required = false) String partial_name) {
        List<Unit> partial = unitService.findUnit(maplnfo_name,single_name,unit_engineering,partial_name);
        List l = new ArrayList();
        RmoveUtils rmoveUtils = new RmoveUtils();
        List<Unit> newList = rmoveUtils.getNewLists(partial);

        List<Map> ls = new ArrayList();
        for (Unit p : newList) {
            Map mapp = new HashMap();
            mapp.put("bop", p.getBop());
            mapp.put("partial_name", p.getPartial_name());
            mapp.put("project_name", p.getProject_name());
            String wo = p.getSignalment().replace("wo", "\r\n");
            mapp.put("signalment", wo);
            mapp.put("prickle", p.getPrickle());
            mapp.put("amount", p.getAmount());
            mapp.put("integrated_unit_price", p.getIntegrated_unit_price());
            mapp.put("sumdata", DecimalUtils.mul(p.getAmount(), p.getIntegrated_unit_price(), 2));
            ls.add(mapp);
        }


        //按照分部分项工程分类
        Map<Object, List> map1 = new HashMap();
        for (Map map : ls) {
            String unit1 = (String) map.get("partial_name");
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
            map.put("prickle", unity);
            map.put("nodes", maps.getValue());
            list1.add(map);
        }
        return list1;
    }



}
