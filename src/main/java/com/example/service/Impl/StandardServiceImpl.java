package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.StandardTable;
import com.example.mapper.StandardMapper;
import com.example.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "StandardService")
public class StandardServiceImpl implements StandardService {
    @Autowired
    private StandardMapper standardMapper;


    @Override
    @DS("datasource1")
    public List<StandardTable> findStandard(String project_name) {
        return standardMapper.selectStandard(project_name);
    }
}
