package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.Total;
import com.example.mapper.TotalMapper;
import com.example.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "TotalService")
public class TotalServiceImpl implements TotalService {
    @Autowired
    private TotalMapper totalMapper;


    @Override
    @DS("datasource2")
    public List<Total> findTotal(String maplnfo_name, String single_name, String unit_engineering) {
        return totalMapper.selectTotal(maplnfo_name, single_name, unit_engineering);
    }


    @Override
    @DS("datasource2")
    public List<Total> findAllTotals(String maplnfo_name, String single_name, String unit_engineering) {
        return totalMapper.selectAllTotals(maplnfo_name, single_name, unit_engineering);
    }

    @Override
    @DS("datasource2")
    public List<Total> findSalf(String maplnfo_name, String single_name, String unit_engineering) {
        return totalMapper.selectSalf(maplnfo_name, single_name, unit_engineering);
    }

    @Override
    @DS("datasource2")
    public List<Total> findProjectName() {
        return totalMapper.selectProjectName();
    }




    @Override
    @DS("datasource2")
    public boolean loadT2ToT1(String maplnfo_name,  String single_name) {
        try {
           totalMapper.insertT2ToT2(maplnfo_name,single_name);
        } catch (Exception e) {
            if (e!=null){
                return false;
            }
            e.printStackTrace();
        }finally {
            return true;
        }
    }
}
