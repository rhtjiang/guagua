package com.example.pojo;

import com.example.entity.Label;
import com.example.entity.LabelCar;
import com.example.entity.Partial;
import com.example.entity.Unit;
import com.example.service.LabelCarService;
import com.example.service.LabelService;
import com.example.service.PartialService;
import com.example.service.UnitService;
import com.example.util.DecimalUtils;

import java.util.*;

public class LabelCat {


    /**
     * //测试标签项
     *
     * @param maplnfo_name
     * @param single_name
     * @param labelService
     * @param partialService
     * @param unitService
     * @return
     */
    public Map selectlabel(String maplnfo_name, String single_name, LabelService labelService, PartialService partialService, UnitService unitService) {

        Set<String> set = new HashSet<>();
        List<Map<String, String>> list = new ArrayList<>();
        //分部分项表项目特征
        List<Partial> partials = partialService.findPartial(maplnfo_name, single_name, null, null);
        //单价措施项目特征
        List<Unit> units = unitService.findUnit(maplnfo_name, single_name, null, null);
        //针对分部分项清单的标签
        List<Label> labels = labelService.findLabel("1", null, null);
        //针对单价措施的标签
        List<Label> labels2 = labelService.findLabel("2", null, null);
        //循环获取分部分项清单特征描述
        for (Partial p : partials) {
            String signalment = p.getSignalment();
            //循环获取标签项
            for (Label l : labels) {
                String field = l.getField();
                String names = l.getName();
                String wo = null;
                if (signalment.contains(names)) {
                    String substring = signalment.substring(signalment.indexOf(names));
                    String substring1 = substring.substring(substring.indexOf("：") + 1);
                    if (substring1.contains("wo")) {
                        wo = substring1.substring(0, substring1.indexOf("wo"));
                    } else {
                        wo = substring1;
                    }
                    if (set.add(wo)) {
                        //将每个提取的标签放入到map2中
                        Map<String, String> map2 = new HashMap();
                        map2.put(field, wo);
                        list.add(map2);
                    }
                }
            }
        }
        //循环获取单价措施清单特征描述
        for (Unit u : units) {
            String signalment = u.getSignalment();
            //循环获取标签项
            for (Label l2 : labels2) {
                String field2 = l2.getField();
                String names2 = l2.getName();
                String wo2 = null;
                if (signalment.contains(names2)) {
                    String substring = signalment.substring(signalment.indexOf(names2));
                    String substring1 = substring.substring(substring.indexOf("：") + 1);
                    if (substring1.contains("wo")) {
                        wo2 = substring1.substring(0, substring1.indexOf("wo"));
                    } else {
                        wo2 = substring1;
                    }
                    if (set.add(wo2)) {
                        //将每个提取的标签放入到map2中
                        Map<String, String> map3 = new HashMap();
                        map3.put(field2, wo2);
                        list.add(map3);
                    }
                }
            }
        }
        //循环list进行分类
        Map<String, List> mapn = new HashMap();
        for (Map map : list) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                if (!mapn.containsKey(key)) {
                    List newList = new ArrayList<>();
                    newList.add(map.get(key));
                    mapn.put(key, newList);
                } else {
                    mapn.get(key).add(map.get(key));
                }
            }
        }
        return mapn;
    }


    /**
     * //通过标签项查询关联项
     *
     * @param maplnfo_name
     * @param single_name
     * @param label
     * @param partialService
     * @param unitService
     * @return
     */
    public List findLabe(String maplnfo_name, String single_name, String label, PartialService partialService, UnitService unitService) {
        List list = new ArrayList();
        //查询所有的分部分项特征项目描述
        List<Partial> partials = partialService.findPartial(maplnfo_name, single_name, null, null);

        //循环获取所有的分部分项特征描述
        for (Partial p : partials) {

            String signalment = p.getSignalment();
            if (signalment.contains(label)) {
                String wo = p.getSignalment().replace("wo", "\r\n");
                p.setSignalment(wo);
                list.add(p);
            }
        }

        //查询所有的单价措施特征项目描述
        List<Unit> units = unitService.findUnit(maplnfo_name, single_name, null, null);

        for (Unit u : units) {

            String signalment = u.getSignalment();
            if (signalment.contains(label)) {
                String wo = u.getSignalment().replace("wo", "\r\n");
                u.setSignalment(wo);
                list.add(u);
            }
        }


        return list;


    }

    /**
     * 获取和点击的编码项目相同的工程量的清单项(分部分项）
     */
    public List selectCo2(String maplnfo_name, String single_name, Double amount, String coding, PartialService partialService) {
        List list = new ArrayList();

        //查询所有的分部分项
        List<Partial> partials = partialService.findPartial(maplnfo_name, single_name, null, null);
        for (Partial p : partials) {
            Map map = new HashMap<>();
            if (p.getAmount() == amount) {
                if (!p.getBop().equals(coding)) {
                    list.add(p);
                }
            }
        }
        return list;
    }


    /**
     * 获取和点击的编码项目相同的工程量的清单项(单价措施）
     */
    public List selectUnitCo2(String maplnfo_name, String single_name, Double amount, String coding, UnitService unitService) {
        List list = new ArrayList();

        //查询所有的单价措施
        List<Unit> units = unitService.findUnit(maplnfo_name, single_name, null, null);
        for (Unit p : units) {
            Map map = new HashMap<>();
            if (p.getAmount() == amount) {
                if (!p.getBop().equals(coding)) {
                    list.add(p);
                }
            }
        }
        return list;
    }


    //将新加入的项目的分部分项清单项进行拆分
    public List<LabelCar> selectLabel222(String maplnfo_name, String single_name, LabelCarService labelCarService, PartialService partialService, UnitService unitService) {
        //计算清单项指标作为标签项
        //分部分项表项目特征及工程量
        List<Partial> partials = partialService.findPartial(maplnfo_name, null, null, null);

        //  double areas2 = unitService.findAreas(maplnfo_name, null);
        List<Unit> areas1 = unitService.findAreas(maplnfo_name, null);

        List<LabelCar> list = new ArrayList();
        //存入对象中
        for (Partial p : partials) {
            //项目的关联标识
            String uid = p.getUid();
            //单项工程名称
            String single_name1 = p.getSingle_name();
            double areas = 0;
            //单项工程建筑面积
            for (Unit u : areas1) {
                double amount = u.getAmount();
                String single_name2 = u.getSingle_name();
                if (single_name1.equals(single_name2)) {
                    areas = amount;
                }
            }
            //工程量
            double amount = p.getAmount();
            //清单项指标
            double v = amount / areas;
            double mul = DecimalUtils.mul(v, 100, 4);
            String signalment = p.getSignalment();
            String[] substring = signalment.split("wo");
            String substring1 = null;
            String substring2 = null;
            for (int i = 0; i < substring.length; i++) {
                String s = substring[i];
                if (s.contains("：") && s.contains(".")) {
                    substring1 = s.substring(2, s.indexOf("："));
                    substring2 = s.substring(s.indexOf("：") + 1);
                } else if (!s.contains(".")) {
                    substring1 = s.substring(0, s.indexOf("："));
                    substring2 = s.substring(s.indexOf("：") + 1);
                } else if (!s.contains("：")) {
                    substring1 = s.substring(s.indexOf(".") + 1);
                    substring2 = s.substring(s.indexOf(".") + 1);
                } else {
                    substring1 = s;
                    substring2 = s;
                }
                LabelCar L = new LabelCar();
                L.setUid(uid + substring1);
                L.setLabel_id(uid);
                L.setLabel_name(substring1);
                L.setLabel_value(substring2);
                list.add(L);
            }
            LabelCar L1 = new LabelCar();
            L1.setUid(uid + "指标");
            L1.setLabel_id(uid);
            L1.setLabel_name("指标");
            L1.setLabel_value(String.valueOf(mul));
            list.add(L1);

        }


        return list;

    }


}
