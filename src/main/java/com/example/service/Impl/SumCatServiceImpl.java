package com.example.service.Impl;

import com.example.config.DS;
import com.example.entity.SumCats;
import com.example.mapper.SumCatMapper;
import com.example.service.SumCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "SumCatService")
public class SumCatServiceImpl implements SumCatService {

    @Autowired
    private SumCatMapper sumCatMapper;


    @Override
    @DS("datasource2")
    public List<SumCats> findSumM(Integer id) {
        return sumCatMapper.selectSumM(id);
    }


    //测试kudu的增删改的功能，普通的传统数据库修改成功会返回受影响行数，但是测试了kudu之后发现一直返回的是-1设置了jdbc的参数后也没有任何反应，网上对这方面的
    //知识也比较少，现在只能采用这种笨办法来实现这些。效率上受到了严重的影响，后期再考虑是否可以在前端来做这方面的事情
    @Override
    @DS("datasource2")
    public boolean modifyK(SumCats sumCats) {
        int i = sumCatMapper.updateK(sumCats);
        List<SumCats> sumCats1 = sumCatMapper.selectSumM(sumCats.getId());
        for (SumCats s:sumCats1) {
            if (!sumCats.getName().equals(s.getName())){
                return false;
            }
        }
        return true;


    }



    @Override
    @DS("datasource2")
    public boolean moveK(Integer id) {
        int i = sumCatMapper.deleteK(id);
        List<SumCats> sumCats = sumCatMapper.selectSumM(null);

        if(!sumCats.contains(id)){
            return true;
        }
        return false;
    }

    @Override
    @DS("datasource2")
    public boolean addK(SumCats sumCats) {
        int i = sumCatMapper.insertK(sumCats);
        System.out.println(i+"增加");
        if (i>0){
            return true;
        }else {
            return false;
        }

    }


}
