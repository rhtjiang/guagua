package com.example.Controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.LabelCar;
import com.example.entity.Single;
import com.example.entity.Units;
import com.example.excel.CopyExcelSheetToAnotherExcelSheet;
import com.example.pojo.LabelCat;
import com.example.pojo.SumCat;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mysql.jdbc.CharsetMapping.MAP_SIZE;


@Controller
public class ExcelUploadController {

    //初始化Service
    @Autowired
    private LabelService labelService;
    @Autowired
    private LabelCarService labelCarService;
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


    @RequestMapping(value = "/excel")
    public String excelup() {
        return "excelupload";
    }

    //文件上传的地址
    public final static String UPLOAD_FILE_PATH = "F:\\test\\";

    //public final static String UPLOAD_FILE_PATH = "/bigdata/upload/";
    @RequestMapping(value = "/uploads", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String uploads(@RequestParam("file") MultipartFile files) {
        String build_type = null;
        if (!files.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(MAP_SIZE);
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(UPLOAD_FILE_PATH, files.getOriginalFilename())));
                out.write(files.getBytes());
                out.flush();
                out.close();
                CopyExcelSheetToAnotherExcelSheet cop = new CopyExcelSheetToAnotherExcelSheet();
                String maplnfo_name = cop.copyExcel(files.getOriginalFilename(), build_type);
                if (maplnfo_name != null) {
                    //调用方法
                    LabelCat labelCat = new LabelCat();
                    //获取标签项目存入kudu中
                    List<LabelCar> list = labelCat.selectLabel222(maplnfo_name, null, labelCarService, partialService, unitService);
                    labelCarService.loadLabelCar(list);
                    //将单项工程分组汇总存入mysql
                    SumCat sumCat = new SumCat();
                    List<Map<String, Object>> result = sumCat.AllCat(maplnfo_name, partialService, unitService, totalService, feesService);
                    List<Single> lists = new ArrayList<>();
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
                        lists.add(single);
                    }
                    boolean b = saveSumService.loadSingle(lists);
                    //将单位工程按照分组存入mysql
                    List<List<Map<String, Object>>> listss = sumCat.AllCatFs(maplnfo_name, partialService, unitService, totalService, feesService);
                    List<Units> lists2 = new ArrayList<>();
                    for (List<Map<String, Object>> maps : listss) {
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
                            lists2.add(units);
                        }
                    }
                    boolean b2 = saveSumService.loadUnits(lists2);
                }
            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            return JSONObject.toJSONString(resObj);
        } else {
            return null;
        }

    }


}
