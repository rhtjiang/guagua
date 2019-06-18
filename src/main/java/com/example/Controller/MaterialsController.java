package com.example.Controller;

import com.example.entity.Materials;
import com.example.entity.Unit;
import com.example.service.MaterialsService;
import com.example.service.UnitService;
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
public class MaterialsController {


    //初始化service
    @Autowired
    private MaterialsService materialsService;
    @Autowired
    private UnitService unitService;


    /**
     * 通过项目名称，单项工程名称，以及单位工程名称获取材料明细表
     */
    @RequestMapping("/table/materials")
    @ResponseBody
    public List selectMaterials(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering) {
        //材料清单
        List<Materials> materials = materialsService.findMaterials(maplnfo_name, single_name, unit_engineering);
        //单项工程面积
      //  double areas = unitService.findAreas(maplnfo_name, single_name);
        List list = new ArrayList<>();
        for (Materials m : materials) {
            Map map = new HashMap<>();
            String name = m.getName();
            String units = m.getUnits();
            double amont = m.getAmont();
            double unit_money = m.getUnit_money();
            double mul = DecimalUtils.mul(amont, unit_money,2);
            map.put("name", name);
            map.put("units", units);
            map.put("amont", amont);
            map.put("unit_money", unit_money);
            map.put("sumdata", mul);
            list.add(map);
        }
        return list;
    }




    /**
     * //材料指标
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @return
     */
    @RequestMapping("/analyma")
    @ResponseBody
    public List analyMater(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering) {
        //材料清单
        List<Materials> materials = materialsService.findMaterials(maplnfo_name, single_name, unit_engineering);
        //单项工程面积
        //double areas = unitService.findAreas(maplnfo_name, single_name);
        List<Unit> areas1 = unitService.findAreas(maplnfo_name, single_name);
        double amount1 = areas1.get(0).getAmount();
        double areas = amount1;
        List list = new ArrayList<>();
        for (Materials m : materials) {
            Map map = new HashMap<>();
            String name = m.getName();
            String units = m.getUnits();
            double amont = m.getAmont();
            double round = (amont/areas);
            double index = DecimalUtils.mul(round,100,4);

            map.put("pname", name);
            map.put("units", units);
            map.put("index", index);
            list.add(map);
        }
        return list;

    }


}
