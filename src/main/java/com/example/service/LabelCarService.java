package com.example.service;

import com.example.entity.LabelCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LabelCarService {


    void loadLabelCar(@Param("list") List<LabelCar> list);
}
