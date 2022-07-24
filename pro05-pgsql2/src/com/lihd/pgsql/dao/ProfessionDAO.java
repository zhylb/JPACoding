package com.lihd.pgsql.dao;

import com.lihd.pgsql.pojo.Profession;

import javax.persistence.Query;
import java.util.List;
import java.util.Random;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/22 23:26
 */
public class ProfessionDAO extends BaseDAO<Profession>{

    public Profession getRandom(List<Profession> professions){
        int size = professions.size();
        Random r= new Random();
        int i = r.nextInt(size);
        return professions.get(i);
    }


}
