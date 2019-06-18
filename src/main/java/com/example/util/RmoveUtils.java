package com.example.util;

import com.example.entity.Partial;
import com.example.entity.Unit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RmoveUtils {

    /**
     * 对分部分项工程进行去重
     * @param oldList
     * @return
     */
    public List<Partial> getNewList(List<Partial> oldList){
        HashMap<String,Partial> tempMap = new HashMap<String,Partial>();
        //去掉重复的key
        for(Partial person : oldList){
            String temp = person.getBop();
            //containsKey(Object key)该方法判断Map集合中是否包含指定的键名，如果包含返回true，不包含返回false
            //containsValue(Object value)该方法判断Map集合中是否包含指定的键值，如果包含返回true，不包含返回false
            if(tempMap.containsKey(temp)){
                Partial newPerson = new Partial();
                newPerson.setBop(temp);
                //合并相同key的value
                newPerson.setSignalment(tempMap.get(temp).getSignalment() + person.getSignalment());
                newPerson.setAmount(person.getAmount());
                newPerson.setProject_name(person.getProject_name());
                newPerson.setPrickle(person.getPrickle());
                newPerson.setIntegrated_unit_price(person.getIntegrated_unit_price());
                newPerson.setMaplnfo_name(person.getMaplnfo_name());
                newPerson.setSingle_name(person.getSingle_name());
                newPerson.setUnit_engineering(person.getUnit_engineering());
                newPerson.setPartial_name(person.getPartial_name());
                //HashMap不允许key重复，当有key重复时，前面key对应的value值会被覆盖
                tempMap.put(temp,newPerson);
            }else{
                tempMap.put(temp,person );
            }
        }
//去除重复key的list
        List<Partial> newList = new ArrayList<Partial>();
        for(String temp:tempMap.keySet()){
            newList.add(tempMap.get(temp));
        }
        return newList;
    }


    /**
     * 对单价措施进行去重
     * @param oldList
     * @return
     */
    public List<Unit> getNewLists(List<Unit> oldList){
        HashMap<String,Unit> tempMap = new HashMap<String,Unit>();
        //去掉重复的key
        for(Unit person : oldList){
            String temp = person.getBop();
            //containsKey(Object key)该方法判断Map集合中是否包含指定的键名，如果包含返回true，不包含返回false
            //containsValue(Object value)该方法判断Map集合中是否包含指定的键值，如果包含返回true，不包含返回false
            if(tempMap.containsKey(temp)){
                Unit newPerson = new Unit();
                newPerson.setBop(temp);
                //合并相同key的value
                newPerson.setSignalment(tempMap.get(temp).getSignalment() + person.getSignalment());
                newPerson.setAmount(person.getAmount());
                newPerson.setProject_name(person.getProject_name());
                newPerson.setPrickle(person.getPrickle());
                newPerson.setIntegrated_unit_price(person.getIntegrated_unit_price());
                newPerson.setMaplnfo_name(person.getMaplnfo_name());
                newPerson.setSingle_name(person.getSingle_name());
                newPerson.setUnit_engineering(person.getUnit_engineering());
                newPerson.setPartial_name(person.getPartial_name());
                //HashMap不允许key重复，当有key重复时，前面key对应的value值会被覆盖
                tempMap.put(temp,newPerson);
            }else{
                tempMap.put(temp,person );
            }
        }
//去除重复key的list
        List<Unit> newList = new ArrayList<Unit>();
        for(String temp:tempMap.keySet()){
            newList.add(tempMap.get(temp));
        }
        return newList;
    }







}
