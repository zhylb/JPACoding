package com.lihd.jpa.junit;

import com.lihd.jpa.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 20:32
 */
public class CacheTest {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void init(){
        factory = Persistence.createEntityManagerFactory("jpa-pro01");
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @After
    public void destroy(){
        transaction.commit();
        entityManager.close();
        factory.close();
    }

    @Test
    public void testHelloJPQL(){

        String jpql = "FROM t_user u WHERE u.id > ?";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,1);

        List resultList = query.getResultList();

        System.out.println(resultList);


    }


    @Test
    public void testCache(){
        //结果只有一条select语句 一级缓存
        User user1 = entityManager.find(User.class, 1);
        User user2 = entityManager.find(User.class, 1);
        System.out.println(user1 == user2);

    }

    @Test
    public void testCacheTwo(){
        User user1 = entityManager.find(User.class, 1);
        transaction.commit();
        entityManager.close();
        factory.close();
        factory = Persistence.createEntityManagerFactory("jpa-pro01");
        entityManager = factory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        User user2 = entityManager.find(User.class, 1);
        System.out.println(user1 == user2);
    }

}
