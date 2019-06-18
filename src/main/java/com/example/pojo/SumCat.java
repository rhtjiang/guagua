package com.example.pojo;

import com.example.entity.*;
import com.example.service.FeesService;
import com.example.service.PartialService;
import com.example.service.TotalService;
import com.example.service.UnitService;
import com.example.util.DecimalUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

public class SumCat {


    /**
     * 通过项目名称，按照单项工程汇总
     *
     * @param maplnfo_name
     * @return
     */
    public List<Map<String, Object>> AllCat(String maplnfo_name, PartialService partialService, UnitService unitService, TotalService totalService, FeesService feesService) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //通过建设项目名称，单项工程名称，按照单位工程名称汇总分部分项数据
        List<Partial> partialSums = partialService.findAllPars(maplnfo_name, null, null, null);
        for (Partial patial : partialSums) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("single_name", patial.getSingle_name());
            map1.put("money", patial.getAmount());
            map1.put("money1", 0);
            map1.put("money2", 0);
            list.add(map1);
        }
        //通过建设项目名称，单项工程名称，按照单位工程名称汇总单价措施数据
        List<Unit> unitSums = unitService.findAllUnits(maplnfo_name, null, null, null);
        for (Unit unitSum : unitSums) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("single_name", unitSum.getSingle_name());
            map2.put("money", unitSum.getAmount());
            map2.put("money1", 0);
            map2.put("money2", 0);
            list.add(map2);
        }
        //通过建设项目名称，单项工程名称，按照单位工程查询总价措施数据
        List<Total> allSums = totalService.findAllTotals(maplnfo_name, null, null);
        for (Total allSum : allSums) {
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("single_name", allSum.getSingle_name());
            map3.put("money", allSum.getMoney());
            map3.put("money1", 0);
            map3.put("money2", 0);
            list.add(map3);
        }
        //通过建设项目名称，单项工程名称，按照单位工程查询规费税金措施数据
        List<Fees> taxesSums = feesService.findAllFees(maplnfo_name, null, null);
        for (Fees taxesSum : taxesSums) {
            Map<String, Object> map4 = new HashMap<String, Object>();
            map4.put("single_name", taxesSum.getSingle_name());
            map4.put("money", taxesSum.getMoney());
            map4.put("money1", 0);
            map4.put("money2", 0);
            list.add(map4);
        }

        //通过建设项目名称，单项工程名称，按照单位工程查询规费数据
        List<Fees> feesSums = feesService.findFe(maplnfo_name, null, null);

        for (Fees feesSum : feesSums) {
            Map<String, Object> map5 = new HashMap<String, Object>();
            map5.put("single_name", feesSum.getSingle_name());
            map5.put("money", 0);
            map5.put("money1", feesSum.getMoney());
            map5.put("money2", 0);
            list.add(map5);
        }
        //通过建设项目名称，单项工程名称，按照单位工程查询安全文明数据
        List<Total> salfSums = totalService.findSalf(maplnfo_name, null, null);

        for (Total salfSum : salfSums) {
            Map<String, Object> map6 = new HashMap<String, Object>();
            map6.put("single_name", salfSum.getSingle_name());
            map6.put("money", 0);
            map6.put("money1", 0);
            map6.put("money2", salfSum.getMoney());
            list.add(map6);
        }


        //循环获取总和数据
        List<Map<String, Object>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String single_name = list.get(i).get("single_name").toString();
            Double money = Double.parseDouble(list.get(i).get("money").toString());
            int flag = 0;// 0为新增数据，1为增加count
            for (int j = 0; j < result.size(); j++) {

                String new_single_name = list.get(j).get("single_name").toString();
                Double new_money = Double.parseDouble(list.get(j).get("money").toString());
                if (single_name.equals(new_single_name)) {
                    double num = Double.parseDouble(list.get(i).get("money").toString())
                            + Double.parseDouble(result.get(j).get("money").toString());
                    double num1 = Double.parseDouble(list.get(i).get("money1").toString())
                            + Double.parseDouble(result.get(j).get("money1").toString());
                    double num2 = Double.parseDouble(list.get(i).get("money2").toString())
                            + Double.parseDouble(result.get(j).get("money2").toString());
                    result.get(j).put("money", DecimalUtils.mul(num, 1, 2));
                    result.get(j).put("money1", DecimalUtils.mul(num1, 1, 2));
                    result.get(j).put("money2", DecimalUtils.mul(num2, 1, 2));
                    flag = 1;
                    continue;
                }
            }
            if (flag == 0) {
                result.add(list.get(i));
            }
        }

        return result;
    }

    /**
     * 通过点击单项工程，按照单位工程分组获取汇总求和
     *
     * @param maplnfo_name
     * @param partialService
     * @param unitService
     * @param totalService
     * @param feesService
     * @return
     */
    public List<List<Map<String, Object>>> AllCatFs(String maplnfo_name, PartialService partialService, UnitService unitService, TotalService totalService, FeesService feesService) {

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        //通过建设项目名称，单项工程名称，按照单位工程名称汇总分部分项数据
        List<Partial> partialSums = partialService.findAllPars(maplnfo_name, "1", null, null);
        for (Partial patial : partialSums) {
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("maplnfo_name", patial.getMaplnfo_name());
            map1.put("single_name", patial.getSingle_name());
            map1.put("unit_engineering", patial.getUnit_engineering());
            map1.put("moneys", patial.getAmount());
            map1.put("money1", 0);
            map1.put("money2", 0);
            list.add(map1);
        }
        //通过建设项目名称，单项工程名称，按照单位工程名称汇总单价措施数据
        List<Unit> unitSums = unitService.findAllUnits(maplnfo_name, "1", null, null);
        for (Unit unitSum : unitSums) {
            Map<String, Object> map2 = new HashMap<String, Object>();
            map2.put("maplnfo_name", unitSum.getMaplnfo_name());
            map2.put("single_name", unitSum.getSingle_name());
            map2.put("unit_engineering", unitSum.getUnit_engineering());
            map2.put("moneys", unitSum.getAmount());
            map2.put("money1", 0);
            map2.put("money2", 0);
            list.add(map2);
        }
        //通过建设项目名称，单项工程名称，按照单位工程查询总价措施数据
        List<Total> allSums = totalService.findAllTotals(maplnfo_name, "1", null);
        for (Total allSum : allSums) {
            Map<String, Object> map3 = new HashMap<String, Object>();
            map3.put("maplnfo_name", allSum.getMaplnfo_name());
            map3.put("single_name", allSum.getSingle_name());
            map3.put("unit_engineering", allSum.getUnit_engineering());
            map3.put("moneys", allSum.getMoney());
            map3.put("money1", 0);
            map3.put("money2", 0);
            list.add(map3);
        }
        //通过建设项目名称，单项工程名称，按照单位工程查询规费税金措施数据
        List<Fees> taxesSums = feesService.findAllFees(maplnfo_name, "1", null);
        for (Fees taxesSum : taxesSums) {
            Map<String, Object> map4 = new HashMap<String, Object>();
            map4.put("maplnfo_name", taxesSum.getMaplnfo_name());
            map4.put("single_name", taxesSum.getSingle_name());
            map4.put("unit_engineering", taxesSum.getUnit_engineering());
            map4.put("moneys", taxesSum.getMoney());
            map4.put("money1", 0);
            map4.put("money2", 0);
            list.add(map4);
        }

        //通过建设项目名称，单项工程名称，按照单位工程查询规费数据
        List<Fees> feesSums = feesService.findFe(maplnfo_name, "1", null);

        for (Fees feesSum : feesSums) {
            Map<String, Object> map5 = new HashMap<String, Object>();
            map5.put("maplnfo_name", feesSum.getMaplnfo_name());
            map5.put("single_name", feesSum.getSingle_name());
            map5.put("unit_engineering", feesSum.getUnit_engineering());
            map5.put("moneys", 0);
            map5.put("money1", feesSum.getMoney());
            map5.put("money2", 0);
            list.add(map5);

        }
        //通过建设项目名称，单项工程名称，按照单位工程查询安全文明数据
        List<Total> salfSums = totalService.findSalf(maplnfo_name, "1", null);

        for (Total salfSum : salfSums) {
            Map<String, Object> map6 = new HashMap<String, Object>();
            map6.put("maplnfo_name", salfSum.getMaplnfo_name());
            map6.put("single_name", salfSum.getSingle_name());
            map6.put("unit_engineering", salfSum.getUnit_engineering());
            map6.put("moneys", 0);
            map6.put("money1", 0);
            map6.put("money2", salfSum.getMoney());
            list.add(map6);

        }
        //按照单项工程分类
        Map<String, List<Map>> map = new HashMap<>();
        for (Map map1 : list) {
            if (map.containsKey(map1.get("single_name"))) {
                map.get(map1.get("single_name")).add(map1);

            } else {
                List<Map> tmpList = new ArrayList<>();
                tmpList.add(map1);
                map.put((String) map1.get("single_name"), tmpList);
            }

        }
        //分类后，循环获取每一个单项工程汇总数据
        List<List<Map<String, Object>>> lists = new ArrayList();
        for (Map.Entry<String, List<Map>> m : map.entrySet()) {
            //循环获取总和数据
            String key = m.getKey();
            List<Map> value = m.getValue();
            List<Map<String, Object>> result = new ArrayList<>();
            for (int i = 0; i < value.size(); i++) {
                String unit_engineering = value.get(i).get("unit_engineering").toString();
                Double money = Double.parseDouble(value.get(i).get("moneys").toString());
                int flag = 0;// 0为新增数据，1为增加count
                for (int j = 0; j < result.size(); j++) {

                    String new_unit_engineering = value.get(j).get("unit_engineering").toString();
                    Double new_money = Double.parseDouble(value.get(j).get("moneys").toString());
                    if (unit_engineering.equals(new_unit_engineering)) {
                        double num = Double.parseDouble(value.get(i).get("moneys").toString())
                                + Double.parseDouble(result.get(j).get("moneys").toString());

                        double num1 = Double.parseDouble(value.get(i).get("money1").toString())
                                + Double.parseDouble(result.get(j).get("money1").toString());
                        double num2 = Double.parseDouble(value.get(i).get("money2").toString())
                                + Double.parseDouble(result.get(j).get("money2").toString());
                        result.get(j).put("moneys", DecimalUtils.mul(num, 1, 2));
                        result.get(j).put("money1", DecimalUtils.mul(num1, 1, 2));
                        result.get(j).put("money2", DecimalUtils.mul(num2, 1, 2));
                        flag = 1;
                        continue;
                    }
                }
                if (flag == 0) {

                    result.add(value.get(i));
                }
            }
            lists.add(result);
        }


        return lists;


    }

}
