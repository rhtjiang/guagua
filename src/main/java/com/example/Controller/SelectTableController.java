package com.example.Controller;

import com.example.entity.Approval;
import com.example.entity.Record;
import com.example.entity.list_dic;
import com.example.service.SelectTableService;
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
public class SelectTableController {


    @Autowired
    private SelectTableService selectTableService;


    /**
     * 通过项目名称，单项工程名称，单位工程名称，分部分项名称，清单项名称来获取每一个清单项具体需要的表格
     *
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @param partial_name
     * @param project_name
     * @return
     */
    @RequestMapping("/listtable")
    @ResponseBody
    public List selectTable(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering, @RequestParam(value = "partial_name", required = false) String partial_name, @RequestParam(value = "project_name", required = false) String project_name) {


        List<list_dic> tablePar = selectTableService.findTablePar(maplnfo_name, single_name, unit_engineering, partial_name, project_name);

        return tablePar;

    }




    /**
     * 获取该等级下一等级表
     * @param dic_id
     * @return
     */
    @RequestMapping("/selectT")
    @ResponseBody
    public List selectT(@RequestParam(value = "dic_id", required = false) Integer dic_id){
        List<Integer> t_relate = selectTableService.findT_relate(dic_id);
        List list = new ArrayList();
        for (Integer i:t_relate){
            List<list_dic> t = selectTableService.findT(i);
            for(list_dic l:t){
                Map map = new HashMap();
                map.put("name",l.getName());
                map.put("t_name",l.getT_name());
                map.put("uid",l.getUid());
                list.add(map);
            }
        }
       return list;
    }



    /**
     * 将数据存入回填土施工记录，并且改变表状态为已完成，并且改变其下一层表状态为需填
     * @param maplnfo_name
     * @param single_name
     * @param unit_engineering
     * @param partial_name
     * @param project_name
     * @param signalment
     * @return
     */
    @RequestMapping("/insertRecort")
    @ResponseBody
    public boolean insertRecort(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_engineering", required = false) String unit_engineering, @RequestParam(value = "partial_name", required = false) String partial_name, @RequestParam(value = "project_name", required = false) String project_name, @RequestParam(value = "old_match", required = false) double old_match, @RequestParam(value = "new_match", required = false) double new_match, @RequestParam(value = "signalment", required = false) String signalment, @RequestParam(value = "item_id", required = false) Integer item_id, @RequestParam(value = "dic_id", required = false) Integer dic_id){
        //数据存入到回填土施工记录
        Record record  = new Record();
        record.setMaplnfo_name(maplnfo_name);
        record.setSingle_name(single_name);
        record.setUnit_name(unit_engineering);
        record.setPartial_name(partial_name);
        record.setProject_name(project_name);
        record.setOld_match(old_match);
        record.setNew_match(new_match);
        record.setSignalment(signalment);
        record.setItem_id(item_id);
        boolean b = selectTableService.loadRecord(record);
        boolean b1 = false;
        boolean b2 =false;
        if (b){
            //查找该等级下一等级的id
            List<Integer> t_relate = selectTableService.findT_relate(dic_id);
            //修改回填土清单项中，回填土施工记录表的状态为已完成
             b1 = selectTableService.modifyRecordStatus(item_id, dic_id);
            //修改回填土清单项中，回填土施工记录表下一级的状态为需填
            for (Integer i:t_relate){
                 b2 = selectTableService.modifyRelateStatus(item_id, i);
            }

            if (b1&&b2){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * 将数据回填到强夯地基检验批中
     * @param item_id
     * @return
     */
    @RequestMapping("/record")
    @ResponseBody
    public List selectRecord(@RequestParam(value = "item_id", required = false) Integer item_id){

        List<Record> record = selectTableService.findRecord(item_id);
        return record;
    }


    /**
     * 将数据存入回填土审批表中，并且改变表状态为已完成，并且改变其下一层表状态为需填

     * @return
     */
    @RequestMapping("/insertApproval")
    @ResponseBody
    public boolean insertApproval(@RequestParam(value = "maplnfo_name", required = false) String maplnfo_name, @RequestParam(value = "single_name", required = false) String single_name, @RequestParam(value = "unit_name", required = false) String unit_name, @RequestParam(value = "partial_name", required = false) String partial_name, @RequestParam(value = "project_name", required = false) String project_name, @RequestParam(value = "old_match", required = false) double old_match, @RequestParam(value = "new_match", required = false) double new_match, @RequestParam(value = "signalment", required = false) String signalment, @RequestParam(value = "item_id", required = false) Integer item_id, @RequestParam(value = "dic_id", required = false) Integer dic_id,@RequestParam(value = "uid", required = false) Integer uid){


        //数据存入到回填土施工记录
        Approval approval = new Approval();
        approval.setMaplnfo_name(maplnfo_name);
        approval.setSingle_name(single_name);
        approval.setUnit_name(unit_name);
        approval.setPartial_name(partial_name);
        approval.setProject_name(project_name);
        approval.setOld_match(old_match);
        approval.setNew_match(new_match);
        approval.setSignalment(signalment);
        approval.setItem_id(item_id);
        approval.setDic_id(dic_id);
        boolean b = selectTableService.loadApproval(approval);
        boolean b1 = false;
        boolean b2 =true;
        if (b){
            System.out.println(uid);
            //查找该等级下一等级的id
            List<Integer> t_relate = selectTableService.findT_relate(uid);
            //修改回填土清单项中，回填土施工审批表的状态为已完成
            b1 = selectTableService.modifyRecordStatus(item_id, uid);
            //修改回填土清单项中，回填土施工记录表下一级的状态为需填
            if (t_relate!=null && t_relate.size()!=0){
                for (Integer i:t_relate){
                    b2 = selectTableService.modifyRelateStatus(item_id, i);
                }
            }
            if (b1&&b2){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
       }
    }

}
