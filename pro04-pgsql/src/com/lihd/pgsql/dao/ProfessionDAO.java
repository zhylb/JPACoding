package com.lihd.pgsql.dao;

import com.lihd.pgsql.pojo.Profession;

import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/22 23:26
 */
public class ProfessionDAO extends BaseDAO{



    public void addProfessionDAO(List<Profession> professions){
        for (Profession profession : professions) {
            entityManager.persist(profession);
        }
    }

}
