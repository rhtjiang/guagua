package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.Materials;
import com.example.mapper.MaterialsMapper;
import com.example.service.MaterialsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "MaterialsService")
public class MaterialsServiceImpl implements MaterialsService {

    @Autowired
    private MaterialsMapper materialsMapper;

    @Override
    @DS("datasource2")
    public List<Materials> findMaterials(String maplnfo_name,String single_name,String unit_engineering) {
        return materialsMapper.selectMaterials(maplnfo_name,single_name,unit_engineering);
    }

    @Override
    @DS("datasource2")
    public boolean loadM2ToM1(String maplnfo_name,  String single_name) {
        try {
            materialsMapper.insertM2ToM2(maplnfo_name,single_name);
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
