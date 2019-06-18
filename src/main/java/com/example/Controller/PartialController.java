package com.example.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.entity.Partial;
import com.example.entity.StandardTable;
import com.example.entity.Total;
import com.example.entity.Unit;
import com.example.mapper.StandardMapper;
import com.example.service.*;
import com.example.util.DecimalUtils;
import com.example.util.RmoveUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class PartialController {

    //初始化service
    @Autowired
    private PartialService partialService;
    @Autowired
    private UnitService unitService;
    @Autowired
    private TotalService totalService;
    @Autowired
    private FeesService feesService;
    @Autowired
    private MaterialsService materialsService;
    @Autowired
    private StandardService standardService;


    /**
     * /查询分部分项表
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @return
     */
    @RequestMapping("/table/maplnfo")
    @ResponseBody
    public List selectPar(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering, @RequestParam(value = "partial_name", required = false) String partial_name) {
        List<Partial> partial = partialService.findPartial(maplnfo_name, single_name, unit_engineering, partial_name);
        List l = new ArrayList();
        RmoveUtils rmoveUtils = new RmoveUtils();
        List<Partial> newList = rmoveUtils.getNewList(partial);
        List<Map> ls = new ArrayList();
        for (Partial p : newList) {
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
            // String unit1 = map.getPartial_name();
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


    /**
     * 分类汇总
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @return
     */
    @RequestMapping("/selctAllPars")
    @ResponseBody
    public List selectAllPars(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering, @RequestParam(value = "partial_name", required = false) String partial_name) {
        List<Partial> allpartials = partialService.findAllPars(maplnfo_name, single_name, unit_engineering, partial_name);
        return allpartials;

    }


    /**
     * //分析分部分项的工程造价指标
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @param partial_name
     * @param money
     * @return
     */
    @RequestMapping("/analypars")
    @ResponseBody
    public List analyPars(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering, @RequestParam(value = "partial_name", required = false) String partial_name, @RequestParam(value = "money", required = false) double money) {
        List list = new ArrayList();
        //单项工程的总造价money
        //单项工程的面积areas
        //  double areas = unitService.findAreas(maplnfo_name, single_name);
        List<Unit> areas1 = unitService.findAreas(maplnfo_name, single_name);
        double amount1 = areas1.get(0).getAmount();
        double areas = amount1;
        //各个分部分项工程的汇总数据
        List<Partial> allPars = partialService.findAllPars(maplnfo_name, single_name, unit_engineering, partial_name);
        for (Partial p : allPars) {
            Map map = new HashMap();
            double round = DecimalUtils.round(p.getAmount(), areas, 2);
            double moneys = DecimalUtils.mul(DecimalUtils.round(p.getAmount(), money, 4), 100, 2);
            map.put("unit_engineering", p.getUnit_engineering());
            map.put("partial_name", p.getPartial_name());
            map.put("round", round);
            map.put("moneys", moneys);
            list.add(map);
        }
        System.out.println(list);
        return list;
    }


    /**
     * //建筑工程实物工程量指标
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @return
     */
    @RequestMapping("/analy")
    @ResponseBody
    public List analyPs(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering) {
        //单项工程的面积areas
        // double areas = unitService.findAreas(maplnfo_name, single_name);
        List<Unit> areas1 = unitService.findAreas(maplnfo_name, single_name);
        double amount1 = areas1.get(0).getAmount();
        double areas = amount1;
        //工程实物量
        List<Partial> partial = partialService.findPartial(maplnfo_name, single_name, unit_engineering, null);
        List<Map> l = new ArrayList();
        RmoveUtils rmoveUtils = new RmoveUtils();
        List<Partial> newList = rmoveUtils.getNewList(partial);
        for (Partial p : newList) {
            Map map = new HashMap();
            double amount = p.getAmount();
            double v = amount / areas;
            double mul = DecimalUtils.mul(v, 100, 4);
            map.put("bop", p.getBop());
            map.put("project_name", p.getProject_name());
            map.put("prickle", p.getPrickle());
            map.put("partial_name", p.getPartial_name());
            map.put("mul", mul);
            l.add(map);
        }

        //按照分部分项工程分类
        Map<Object, List> map1 = new HashMap();
        for (Map map : l) {
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

    //////////////多项，，漏项验证
    @RequestMapping("/anaPa")
    @ResponseBody
    //对比模板和新导入项目的分部分项数据查看是否有漏项行为，相似项目中暂时以项目名称作为依据
    public List anapartial(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "m_name", required = false) String m_name) {
        System.out.println(maplnfo_name + "--------------------");

        //获取所有的漏项数据
        List<Partial> partials = partialService.anPartial(maplnfo_name, single_name, m_name);

        //获取漏项条数
        int lesssize = partials.size();

        return partials;


    }


    //对比模板和新导入项目的分部分项数据查看是否有多项行为，相似项目中暂时以项目名称作为依据
    @RequestMapping("/anaPas")
    @ResponseBody
    public List anapartials(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "m_name", required = false) String m_name) {
        //获取所有的多项数据
        List<Partial> partials = partialService.anPartials(maplnfo_name, single_name, m_name);
        //获取多项条数
        int lostsize = partials.size();
        System.out.println(lostsize);
        return partials;


    }


    /**
     * 查询标准项目中建设项目名称
     *
     * @return
     */
    @RequestMapping("/selectName2")
    @ResponseBody
    public List selectName2() {
        List<Partial> projectName = partialService.findProjectName();
        List list = new ArrayList();
        for (Partial s : projectName) {
            String maplnfo_name = s.getMaplnfo_name();
            list.add(maplnfo_name);

        }
        return list;
    }

    /**
     * 查询实例项目名称
     *
     * @return
     */
    @RequestMapping("/selectNames")
    @ResponseBody
    public List selectName() {
        List<Partial> projectName = partialService.findProjectNames();
        List list = new ArrayList();
        for (Partial s : projectName) {
            String maplnfo_name = s.getMaplnfo_name();
            list.add(maplnfo_name);
        }
        return list;
    }

    /**
     * 查询实例单项项目名称以及缺漏项项目条数
     *
     * @return
     */
    @RequestMapping("/selectSiName")
    @ResponseBody
    public List selectSiName(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "m_name", required = false) String m_name) {

        List list = new ArrayList();

        //获取所有的漏项数据
        List<Partial> partials = partialService.anPartial(maplnfo_name, single_name, m_name);
        //获取漏项条数
        int lesssize = partials.size();
        //获取所有的多项数据
        List<Partial> lostpartials = partialService.anPartials(maplnfo_name, single_name, m_name);
        //获取多项条数
        int lostsize = lostpartials.size();
        Map map1 = new HashMap();
        map1.put("single_name", single_name);
        map1.put("lesssize", lesssize);
        map1.put("lostsize", lostsize);
        list.add(map1);

        return list;
    }

    /**
     * 去重查询实例项目单项工程名称
     *
     * @param maplnfo_name
     * @return
     */
    @RequestMapping("/selectSiName1")
    @ResponseBody
    public List selectSt(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name) {
        List<Partial> projectName = partialService.findSiName(maplnfo_name);

        return projectName;
    }


    /**
     * 查询模板库项目名称
     *
     * @return
     */
    @RequestMapping("/stands")
    @ResponseBody
    public List selectStand() {
        List<Partial> stand = partialService.findStand();
        List list = new ArrayList();
        for (Partial p : stand) {

            list.add(p.getMaplnfo_name());
        }
        return list;
    }

    /**
     * 将漏项清单项加入到实例中
     *
     * @param maplnfo_name
     * @param unit_engineering
     * @param single_name
     * @param partial_name
     * @param bop
     * @param project_name
     * @param signalment
     * @param prickle
     * @param amount
     * @param integrated_unit_price
     * @return
     */
    @RequestMapping(value = "/insert")
    @ResponseBody
    public boolean insertPa(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering,
                            @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "partial_name", required = false) String partial_name,
                            @RequestParam(value = "bop", required = false) String bop, @RequestParam(value = "project_name", required = false) String project_name,
                            @RequestParam(value = "signalment", required = false) String signalment, @RequestParam(value = "prickle", required = false) String prickle,
                            @RequestParam(value = "amount", required = false) Double amount, @RequestParam(value = "integrated_unit_price", required = false) Double integrated_unit_price) {
        Random rand = new Random(0);
        int i = rand.nextInt(500);
        Partial p = new Partial();
        p.setUid(maplnfo_name + bop + i);
        p.setMaplnfo_name(maplnfo_name);
        p.setUnit_engineering(unit_engineering);
        p.setSingle_name(single_name);
        p.setPartial_name(partial_name);
        p.setBop(bop);
        p.setProject_name(project_name);
        p.setSignalment(signalment);
        p.setPrickle(prickle);
        p.setAmount(amount);
        p.setIntegrated_unit_price(integrated_unit_price);
        boolean load = partialService.load(p);
        return load;

    }


    /**
     * 对多项中的清单项进行删除
     *
     * @param uid
     * @return
     */
    @RequestMapping("/deletePa")
    @ResponseBody
    public boolean delete(@RequestParam(value = "uid", required = false) String uid) {

        boolean b = partialService.removePa(uid);

        return b;
    }


    /**
     * 查询实例表中数据
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @return
     */
    @RequestMapping("/selectP2")
    @ResponseBody
    public List selectPar2(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering, @RequestParam(value = "partial_name", required = false) String partial_name) {
        List<Partial> partial = partialService.findPartial2(maplnfo_name, single_name, unit_engineering, partial_name);
        List l = new ArrayList();
        RmoveUtils rmoveUtils = new RmoveUtils();
        List<Partial> newList = rmoveUtils.getNewList(partial);
        List<Map> ls = new ArrayList();
        for (Partial p : newList) {
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
            // String unit1 = map.getPartial_name();
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


    /**
     * 将实例项目加入到模板中
     *
     * @param m_name
     * @return
     */
    @RequestMapping("/insertStand")
    @ResponseBody
    public boolean insertStand(@RequestParam(value = "m_name", required = false) String m_name, @RequestParam(value = "maplnfo_name", required = false) String maplnfo_name,
                               @RequestParam(value = "single_name", required = false) String single_name) {
        System.out.println(m_name + maplnfo_name + single_name);
        boolean b = partialService.loadStand(maplnfo_name, single_name, m_name);
        return b;
    }

    /**
     * 查询指定模板数据
     *
     * @param m_name
     * @return
     */
    @RequestMapping("/selectSt")
    @ResponseBody
    public List selectStandPa(@RequestParam(value = "m_name", required = false) String m_name) {
        List<Partial> partial = partialService.findStandPa(m_name);
        List l = new ArrayList();
        RmoveUtils rmoveUtils = new RmoveUtils();
        List<Partial> newList = rmoveUtils.getNewList(partial);
        List<Map> ls = new ArrayList();
        for (Partial p : newList) {
            Map mapp = new HashMap();
            mapp.put("bop", p.getBop());
            mapp.put("partial_name", p.getPartial_name());
            mapp.put("project_name", p.getProject_name());
            String wo = p.getSignalment().replace("wo", "\r\n");
            mapp.put("signalment", wo);
            mapp.put("prickle", p.getPrickle());
            ls.add(mapp);
        }
        //按照分部分项工程分类
        Map<Object, List> map1 = new HashMap();
        for (Map map : ls) {
            String unit1 = (String) map.get("partial_name");
            // String unit1 = map.getPartial_name();
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
            map.put("project_name", unity);
            map.put("nodes", maps.getValue());
            list1.add(map);
        }
        return list1;


    }

    /**
     * 将实例库中数据存入到标准项目库中
     *
     * @param maplnfo_name
     * @return
     */
    @RequestMapping("/partial21")
    @ResponseBody
    public boolean loadP2ToP1(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name) {
        boolean b = partialService.loadP2ToP1(maplnfo_name, single_name);
        boolean b1 = unitService.loadU2ToU1(maplnfo_name, single_name);
        boolean b2 = totalService.loadT2ToT1(maplnfo_name, single_name);
        boolean b3 = feesService.loadF2ToF1(maplnfo_name, single_name);
        boolean b4 = materialsService.loadM2ToM1(maplnfo_name, single_name);
        if (b && b1 && b2 && b3 && b4) {
            return true;
        } else {
            return false;
        }
    }


    @RequestMapping("/search")
    @ResponseBody
    public List<Partial> searchMCD(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "searchs", required = false) String searchs, @RequestParam(value = "unit_engineering", required = false) String unit_engineering) {


        List<Partial> partials = partialService.finMCD(maplnfo_name, single_name, unit_engineering, searchs);
        return partials;


    }


    //查询每一个清单项的标签
    @RequestMapping(value = "/paLabel", produces = "application/json;charset=UTF-8")

    public @ResponseBody
    Map selectPaLabel(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name) {

        List<Partial> paLabel = partialService.findPaLabel(maplnfo_name);
        String[] s = {"钢筋混凝土预制桩检验批", "打桩施工记录", "钢桩工程检验批", "土方开挖工程检验批", "土方回填检验批", "强夯地基检验批", "注浆地基检验批", "地基验槽检验批", "钢筋加工检验批", "模板工程检验批", "石砌体工程检验批",
                "砖砌体工程检验批", "高强度螺栓检验批", "网架安装检验批", "防火涂料检验批", "塑钢门窗安装检验批", "裱糊工程检验批", "一般抹灰工程检验批"
                , "玻璃幕墙安装检验批", "门窗玻璃安装检验批", "石材幕墙安装", "楼地面找平", "金属幕墙安装", "整体面层检验批", "屋面找平", "管道工程", "通水实验", "设备工程", "电阻实验", "污水管道", "卫生器具", "锅炉安装", "雨水管道", "制冷设备"
                , "通风设备", "电器工程", "电梯安装", "自动滑梯", "智能建筑","水泥检验"};
        String[] s2 = {"采购","合同","实施","验收","招工","开会","测量","填表","整顿","检查","巡视","运输","安置","商讨","开工","租赁","勘探","奠基","混凝土搅拌","脚手架搭接"};
        Map map1 = new HashMap();

        //循环获取不相同的随机数
        Random random = new Random();
        List lists = new ArrayList();
        for (Partial p:paLabel) {
            Map map2 = new HashMap();
            map2.put("uid",p.getUid());
            map2.put("bop",p.getBop());
            map2.put("project_name",p.getProject_name());
            map2.put("maplnfo_name",p.getMaplnfo_name());
            map2.put("single_name",p.getSingle_name());
            map2.put("unit_engineering",p.getUnit_engineering());
            map2.put("partial_name",p.getPartial_name());
            map2.put("build_type",p.getBuild_type());
            map2.put("LabelCar",p.getLabelCar());
            //存放任务表
            int[] arr2 = new int[4];
            List l2 = new ArrayList();
            arr2[0] = random.nextInt(s2.length);
            //存放质量表
            int[] arr = new int[4];
            List l = new ArrayList();
            arr[0] = random.nextInt(s.length);
            int i = 1;
            //外循环定义四个数质量表格
            while(i <=3) {
                int x = random.nextInt(s.length);
                int x2 = random.nextInt(s2.length);
                /*内循环：新生成随机数和已生成的比较，
                 *相同则跳出内循环，再生成一个随机数进行比较
                 *和前几个生成的都不同则这个就是新的随机数
                 */
                for (int j = 0; j <= i - 1; j++) {
                    //相同则跳出内循环，再生成一个随机数进行比较
                    if (arr[j] == x) {
                        break;
                    }

                    if (arr2[j] == x2) {
                        break;
                    }
                    //执行完循环和前几个生成的都不同则这个就是新的随机数
                    if(j+1==i){
                        arr[i] = x;
                        arr2[i] = x2;
                        i++;
                    }
                }
            }
            for (int aaa : arr) {
               l.add(s[aaa]);
            }

            for (int aaa2 : arr2) {
                l2.add(s2[aaa2]);
            }
            map2.put("tables",l);
            map2.put("works",l2);
            lists.add(map2);
        }
        map1.put("test", lists);
        return map1;
    }


    //查询质量表
    @RequestMapping("/standard")
    @ResponseBody
    public List selectStandard(@RequestParam(value = "project_name", required = false) String project_name) {
        System.out.println(project_name);
        List<StandardTable> standard = standardService.findStandard(project_name);
        List l = new ArrayList();
        for (StandardTable s:standard){
            String table_name = s.getTable_name();
            Map map1 = new HashMap();
            map1.put("table_name",table_name);
            l.add(map1);
        }


        return l;


    }


}
