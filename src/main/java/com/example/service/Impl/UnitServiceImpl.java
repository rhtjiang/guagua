package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.Unit;
import com.example.mapper.UnitMapper;
import com.example.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "UnitService")
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitMapper unitMapper;


    @Override
    @DS("datasource2")
    public List<Unit> findUnit(String maplnfo_name, String single_name, String unit_engineering,String partial_name) {
        return unitMapper.selectUnit(maplnfo_name, single_name, unit_engineering,partial_name);
    }


    @Override
    @DS("datasource2")
    public List<Unit> findAllUnits(String maplnfo_name, String single_name, String unit_engineering,String partial_name) {
        return unitMapper.selectAllUnits(maplnfo_name, single_name, unit_engineering,partial_name);
    }

    @Override
    @DS("datasource2")
    public List<Unit> findAreas(String maplnfo_name, String single_name) {
        return unitMapper.selectAreas(maplnfo_name,single_name);
    }


    @Override
    @DS("datasource2")
    public boolean loadU2ToU1(String maplnfo_name,  String single_name) {
        try {
            unitMapper.insertU2ToU2(maplnfo_name,single_name);
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
