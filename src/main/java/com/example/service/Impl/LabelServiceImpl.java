package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.Label;
import com.example.entity.Partial;
import com.example.mapper.LabelMapper;
import com.example.mapper.PartialMapper;
import com.example.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "LabelService")
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;
    @Autowired
    private PartialMapper partialMapper;

    @Override
    @DS("datasource1")
    public List<Label> findLabel(String major,String name,String field) {
        return labelMapper.selectLabel(major,name,field);
    }

    @Override
    @DS("datasource1")
    public void addLabel(Label label) {
        labelMapper.insertLabel(label);
    }

    @Override
    @DS("datasource1")
    public void modifyLabel(Label label) {
        labelMapper.updateLabel(label);
    }



}
