package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.Approval;
import com.example.entity.Record;
import com.example.entity.list_dic;
import com.example.mapper.SelectTableMapper;
import com.example.service.SelectTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "SelectTableService")
public class SelectTableServiceImpl implements SelectTableService {


    @Autowired
    private SelectTableMapper selectTableMapper;

    @Override
    @DS("datasource1")
    public List<list_dic> findTablePar(String maplnfo_name, String single_name, String unit_engineering, String partial_name, String project_name) {
        return selectTableMapper.selectTablePar(maplnfo_name,single_name,unit_engineering,partial_name,project_name);
    }

    @Override
    @DS("datasource1")
    public boolean loadRecord(Record record) {
        int i = selectTableMapper.insertRecord(record);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    @DS("datasource1")
    public boolean modifyRecordStatus(Integer item_id, Integer dic_id) {
        int i = selectTableMapper.updateRecordStatus(item_id, dic_id);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    @DS("datasource1")
    public boolean modifyRelateStatus(Integer item_id, Integer relate_id) {

        int i = selectTableMapper.updateRelateStatus(item_id, relate_id);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    @DS("datasource1")
    public List<Record> findRecord(Integer item_id) {
        List<Record> records = selectTableMapper.selectRecord(item_id);
        return records;
    }

    @Override
    @DS("datasource1")
    public boolean loadApproval(Approval approval) {
        int i = selectTableMapper.insertApproval(approval);
        if (i>0){
            return true;
        }else {
            return false;
        }

    }


    @Override
    @DS("datasource1")
    public List<Integer> findT_relate(Integer dic_id) {
        return selectTableMapper.selectT_relate(dic_id);
    }

    @Override
    @DS("datasource1")
    public List<list_dic> findT(Integer dic_id) {
        return selectTableMapper.selectT(dic_id);
    }
}
