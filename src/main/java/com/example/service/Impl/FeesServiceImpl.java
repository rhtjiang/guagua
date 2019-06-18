package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.Fees;
import com.example.mapper.FeesMapper;
import com.example.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "FeesService")
public class FeesServiceImpl implements FeesService {
    @Autowired
    private FeesMapper feesMapper;


    @Override
    @DS("datasource2")
    public List<Fees> findFees(String maplnfo_name, String single_name, String unit_engineering) {
        return feesMapper.selectFees(maplnfo_name, single_name, unit_engineering);
    }


    @Override
    @DS("datasource2")
    public List<Fees> findAllFees(String maplnfo_name, String single_name, String unit_engineering) {
        return feesMapper.selectAllFees(maplnfo_name, single_name, unit_engineering);
    }

    @Override
    @DS("datasource2")
    public List<Fees> findFe(String maplnfo_name, String single_name, String unit_engineering) {
        return feesMapper.selectFe(maplnfo_name, single_name, unit_engineering);
    }

    @Override
    @DS("datasource2")
    public boolean loadF2ToF1(String maplnfo_name,  String single_name) {
        try {
            feesMapper.insertF2ToF2(maplnfo_name,single_name);
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
