package com.lihd.jpa.junit;

import com.lihd.jpa.pojo.Book;
import com.lihd.jpa.pojo.User;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/20 15:54
 */
public class UserTest {


    public static <T> void insert(Class<T> clazz){
        //1 创建EntityManagerFactory
        String persistenceUnitName = "jpa-pro01";
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);

        //2 创建EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //3 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        //4 进行持久化操作

        T t = null;
        try {
            t = clazz.newInstance();

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        entityManager.persist(t);

        //5 提交事务
        transaction.commit();

        //6 关闭EntityManager
        entityManager.close();

        //7 关闭EntityManagerFactory
        entityManagerFactory.close();
    }

    /**
     * Test01 :
     */
    @Test
    public void test01 (){
        //1 创建EntityManagerFactory
        String persistenceUnitName = "jpa-pro01";
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName);

        //2 创建EntityManager
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //3 开启事务
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        //4 进行持久化操作
        User user = new User();
        user.setUsername("hello jpa");
        user.setPassword("persist");
        user.setEmail("notnull@notnull.org");
        user.setCreateTime(new Date());
        user.setBirth(new Date());

        entityManager.persist(user);

        //5 提交事务
        transaction.commit();

        //6 关闭EntityManager
        entityManager.close();

        //7 关闭EntityManagerFactory
        entityManagerFactory.close();
    }

    /**
     * Test02 :
     */
    @Test
    public void test02 (){
        insert(Book.class);
    }

}
