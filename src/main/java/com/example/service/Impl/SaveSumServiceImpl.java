package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.Single;
import com.example.entity.Units;
import com.example.mapper.SaveSumMapper;
import com.example.service.SaveSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "SaveSumService")
public class SaveSumServiceImpl implements SaveSumService {
    @Autowired
    private SaveSumMapper saveSumMapper;

    @Override
    @DS("datasource1")
    public boolean loadSingle(List<Single> list) {
        int i = saveSumMapper.insertSingle(list);
        if (i >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    @DS("datasource1")
    public List<Single> findSingle(String maplnfo_name) {
        return saveSumMapper.selectSingle(maplnfo_name);
    }

    @Override
    @DS("datasource1")
    public boolean loadUnits(List<Units> list) {
        int i = saveSumMapper.insertUnits(list);
        if (i >= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Units> findUnits(String maplnfo_name, String single_name) {
        List<Units> units = saveSumMapper.selectUnits(maplnfo_name, single_name);
        return units;
    }
}
