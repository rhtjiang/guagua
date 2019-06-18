package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.LabelCar;
import com.example.mapper.LabelCarMapper;
import com.example.service.LabelCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "LabelCarService")
public class LabelCarServiceImpl implements LabelCarService {

    @Autowired
    private LabelCarMapper labelCarMapper;




    @Override
    @DS("datasource2")
    public void loadLabelCar(List<LabelCar> list) {

         labelCarMapper.insertLabelCar(list);
    }
}
