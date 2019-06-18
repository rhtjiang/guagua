package com.example.Controller;

import com.example.entity.Label;
import com.example.entity.LabelCar;
import com.example.entity.Partial;
import com.example.entity.Unit;
import com.example.pojo.LabelCat;
import com.example.pojo.SumCat;
import com.example.service.LabelCarService;
import com.example.service.LabelService;
import com.example.service.PartialService;
import com.example.service.UnitService;
import com.example.util.DecimalUtils;
import com.example.util.RmoveUtils;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.sql.SQLException;
import java.util.*;

@Controller
public class LabelController {

    @Autowired
    private LabelService labelService;
    @Autowired
    private PartialService partialService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private LabelCarService labelCarService;

    /**
     * 查询所有的标签项
     *
     * @return
     */
    @RequestMapping(value = "/labels")
    @ResponseBody
    public List findLabels(@RequestParam(value = "major", required = false) String major, @RequestParam(value = "name", required = false) String name, @RequestParam(value = "field", required = false) String field) {
        List<Label> label = labelService.findLabel(major, name, field);
        return label;
    }

    /**
     * 增加标签项
     *
     * @param
     */
    @RequestMapping(value = "/add/label")
    @ResponseBody
    public void addLabels(@RequestParam String field, @RequestParam String name, @RequestParam String major) {
        Label label = new Label();
        label.setField(field);
        label.setName(name);
        label.setMajor(major);
        labelService.addLabel(label);
    }

    /**
     * 修改标签项
     *
     * @param
     */
    @RequestMapping(value = "/update/label")
    @ResponseBody
    public void modifyLabels(@RequestParam String field, @RequestParam String name, @RequestParam int id) {
        Label label = new Label();
        label.setField(field);
        label.setName(name);
        label.setId(id);
        labelService.modifyLabel(label);
    }

    /**
     * 找出每个项目的标签项
     *
     * @param maplnfo_name
     * @param single_name
     * @return
     */
    @RequestMapping("/label")
    @ResponseBody
    public List selectLa(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "money", required = false) double money) {
        LabelCat labelCat = new LabelCat();
        List list = new ArrayList();
        SumCat sumCat = new SumCat();
        //获取标签项目
        Map labels = labelCat.selectlabel(maplnfo_name, single_name, labelService, partialService, unitService);
        //获取指定单项工程的面积
        ///double areas = unitService.findAreas(maplnfo_name, single_name);
        List<Unit> areas1 = unitService.findAreas(maplnfo_name, single_name);
        double amount1 = areas1.get(0).getAmount();
        double areas = amount1;
        //计算单方造价
        double round = DecimalUtils.round(money, areas, 2);
        labels.put("allmoney", money);
        labels.put("round", round);
        labels.put("amounts", areas);
        list.add(labels);
        return list;
    }

    /**
     * 通过标签查询相关项
     *
     * @param maplnfo_name
     * @param single_name
     * @param label
     * @return
     */
    @RequestMapping("/relate")
    @ResponseBody
    public List label(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam String label) {

        String replace = label.replace(" ", "+");
        System.out.println(replace + "----------------");
        LabelCat labelCat = new LabelCat();
        List list = new ArrayList();
        List labe = labelCat.findLabe(maplnfo_name, single_name, replace, partialService, unitService);
        list.add(labe);
        return list;
    }


    /**
     * 通过点击清单项获取工程量相同的清单项（分部分项表）
     */
    @RequestMapping("/coding")
    @ResponseBody
    public List selectCoo(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam Double amount, @RequestParam String coding) {
        System.out.println(amount + "---------");
        System.out.println(coding + "++++++++++++++++++++");
        LabelCat labelCat = new LabelCat();
        List list1 = labelCat.selectCo2(maplnfo_name, single_name, amount, coding, partialService);
        //将相同的编码合并成同一条数据
        RmoveUtils rmoveUtils = new RmoveUtils();
        List<Partial> newList = rmoveUtils.getNewList(list1);
        List ls = new ArrayList();
        for (Partial p : newList) {
            Map mapp = new HashMap();
            mapp.put("bop", p.getBop());
            mapp.put("partial_name", p.getPartial_name());
            mapp.put("project_name", p.getProject_name());
            mapp.put("signalment", p.getSignalment());
            mapp.put("prickle", p.getPrickle());
            mapp.put("amount", p.getAmount());
            mapp.put("integrated_unit_price", p.getIntegrated_unit_price());
            mapp.put("sumdata", DecimalUtils.mul(p.getAmount(), p.getIntegrated_unit_price(), 2));
            ls.add(mapp);
        }
        return ls;
    }


    /**
     * 通过点击清单项获取工程量相同的清单项（单价措施表）
     */
    @RequestMapping("/coding/unit")
    @ResponseBody
    public List selectUnitCoo(@RequestParam String maplnfo_name, @RequestParam String single_name, @RequestParam Double amount, @RequestParam String coding) {
        LabelCat labelCat = new LabelCat();
        List list1 = labelCat.selectUnitCo2(maplnfo_name, single_name, amount, coding, unitService);
        //将相同的编码合并成同一条数据
        RmoveUtils rmoveUtils = new RmoveUtils();
        List<Unit> newList = rmoveUtils.getNewLists(list1);
        List ls = new ArrayList();
        for (Unit p : newList) {
            Map mapp = new HashMap();
            mapp.put("bop", p.getBop());
            mapp.put("partial_name", p.getPartial_name());
            mapp.put("project_name", p.getProject_name());
            mapp.put("signalment", p.getSignalment());
            mapp.put("prickle", p.getPrickle());
            mapp.put("amount", p.getAmount());
            mapp.put("integrated_unit_price", p.getIntegrated_unit_price());
            mapp.put("sumdata", DecimalUtils.mul(p.getAmount(), p.getIntegrated_unit_price(), 2));
            ls.add(mapp);
        }
        return ls;
    }


    /**
     * 标签增加
     *
     * @param maplnfo_name
     * @param single_name
     * @return
     */
    /*@RequestMapping("/label22222")
    @ResponseBody
    public List selectLas2(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name) {
        LabelCat labelCat = new LabelCat();

        SumCat sumCat = new SumCat();
        //获取标签项目
        List<LabelCar> list = labelCat.selectLabel222(maplnfo_name, null, labelCarService, partialService, unitService);
        labelCarService.loadLabelCar(list);
        //获取指定单项工程的面积
        double areas = unitService.findAreas(maplnfo_name, single_name);
        //计算单方造价
        double round = DecimalUtils.round(money, areas, 2);
        labels.put("allmoney", money);
        labels.put("round", round);
        labels.put("amounts", areas);
        list.add(labels);
        return list;
    }*/
}


