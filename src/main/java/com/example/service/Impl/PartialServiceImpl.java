package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.Partial;
import com.example.entity.Total;
import com.example.mapper.PartialMapper;
import com.example.service.PartialService;
import org.apache.hive.jdbc.HiveStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "PartialService")
public class PartialServiceImpl implements PartialService {
    static HiveStatement stmt = null;

    @Autowired
    private PartialMapper partialMapper;


    @Override
    @DS("datasource2")
    public List<Partial> findPartial(String maplnfo_name, String single_name, String unit_engineering,String partial_name) {
        return partialMapper.selectPartial(maplnfo_name, single_name, unit_engineering,partial_name);
    }


    @Override
    @DS("datasource2")
    public List<Partial> findAllPars(String maplnfo_name, String single_name, String unit_engineering,String partial_name) {
        return partialMapper.selectAllPars(maplnfo_name, single_name, unit_engineering,partial_name);
    }

    @Override
    @DS("datasource2")
    public List<Partial> anPartial(String maplnfo_name,String single_name,String m_name) {
        return partialMapper.analPar(maplnfo_name,single_name,m_name);
    }

    @Override
    @DS("datasource2")
    public List<Partial> anPartials(String maplnfo_name, String single_name,String m_name) {
        return partialMapper.analpars(maplnfo_name,single_name,m_name);
    }

    @Override
    @DS("datasource2")
    public List<Partial> findProjectName() {
        return partialMapper.selectProjectName();
    }


    @Override
    @DS("datasource2")
    public List<Partial> findProjectNames() {
        return partialMapper.selectProjectNames();
    }

    @Override
    @DS("datasource2")
    public List<Partial> findSiName(String maplnfo_name) {
        return partialMapper.selectSiName(maplnfo_name);
    }

    @Override
    @DS("datasource2")
    public List<Partial> findStand() {
        return partialMapper.selectStand();
    }

    @Override
    @DS("datasource2")
    public boolean load(Partial p) {
        try {
            partialMapper.insertPa(p);
        } catch (Exception e) {
            if (e!=null){
                return false;
            }
            e.printStackTrace();
        }finally {
            return true;
        }

    }

    @Override
    @DS("datasource2")
    public boolean removePa(String uid) {


        try {
            partialMapper.deletePa(uid);
        } catch (Exception e) {
            if (e!=null){
                return false;
            }
            e.printStackTrace();
        }finally {
            return true;
        }
    }

    @Override
    @DS("datasource2")
    public List<Partial> findPartial2(String maplnfo_name, String single_name, String unit_engineering, String partial_name) {
        return partialMapper.selectPartial2(maplnfo_name,single_name,unit_engineering,partial_name);
    }



    @Override
    @DS("datasource2")
    public boolean loadStand(String maplnfo_name, String single_name, String m_name) {

        try {
            partialMapper.insertStand(maplnfo_name,single_name,m_name);
        } catch (Exception e) {
            if (e!=null){
                return false;
            }
            e.printStackTrace();
        }finally {
            return true;
        }


    }

    @Override
    @DS("datasource2")
    public List<Partial> findStandPa(String m_name) {
        List<Partial> partials = partialMapper.selectStandPa(m_name);
        return  partials;
    }

    @Override
    @DS("datasource2")
    public boolean loadP2ToP1(String maplnfo_name,  String single_name) {
        try {
            partialMapper.insertP2ToP2(maplnfo_name,single_name);
        } catch (Exception e) {
            if (e!=null){
                return false;
            }
            e.printStackTrace();
        }finally {
            return true;
        }
    }

    @Override
    @DS("datasource2")
    public List<Partial> finMCD(String maplnfo_name, String single_name, String unit_engineering,String searchs) {
        return partialMapper.searchMCD(maplnfo_name,single_name,unit_engineering,searchs);
    }

    @Override
    @DS("datasource2")
    public List<Partial> findPaLabel(String maplnfo_name) {
        return partialMapper.selectPaLabel(maplnfo_name);
    }


}
