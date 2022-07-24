package com.lihd.pgsql.dao;

import com.lihd.pgsql.pojo.Profession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/22 23:28
 */
public class BaseDAO {


    protected EntityManagerFactory factory;
    protected EntityManager entityManager;
    protected EntityTransaction transaction;

    public BaseDAO(){
        init();
    }


    public void init(){
        factory = Persistence.createEntityManagerFactory("jpa-pro02");
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }


    public void destroy(){
        transaction.commit();
        entityManager.close();
        factory.close();
    }


}
