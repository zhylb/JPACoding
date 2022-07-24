package com.lihd.jpa.junit;

import com.lihd.jpa.pojo.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.beans.Customizer;
import java.util.Date;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 9:58
 */
public class APITest2 {

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

    /*


    refresh会调用select 语句
    作用 保证数据库中的对象 和refresh的对象值保持一致
     */
    @Test
    public void testRefresh(){
        User user = entityManager.find(User.class, 1);
        System.out.println(user);


        entityManager.refresh(user);
        user.setAge(10050);//user的age被设置为10050
        entityManager.refresh(user);//此时user的age不是10050而是数据库中的值
        //AUTO
        System.out.println(entityManager.getFlushMode());


    }

    /*
    默认是提交事务时 刷新缓存

    对于下面的例子
    默认时  会在提交事务时执行update
    调用flush时 会直接执行update但是由于 事务没有提交 数据库的属性不会改变
     */
    @Test
    public void testFlush(){
        User user = entityManager.find(User.class, 1);
        System.out.println(user);

        user.setAge(1002);
        entityManager.flush();
        //AUTO
        System.out.println(entityManager.getFlushMode());


    }

    /*
    如果传入的是一个游离对象 （对象有oid） 满足
    1 在entityManager缓存中有对象
    jpa会把游离对象的属性值赋值到查询到的EntityManager缓存对象中
    EntityManager缓存的对象执行update

    其中有一个复制操作
    并不是对应了两个游离对象id相同（关联了两个对象id相同） 这样在jpa和hibernate都会报错
     */
    @Test
    public void testMerge4(){
        User user = new User("DD","DD",1);
        user.setId(6);
        User user1 = entityManager.find(User.class, 6);

        User merge = entityManager.merge(user);

        //user : 100
        //merge : 6
        System.out.println("user : " + user.getId());//null
        System.out.println("merge : " + merge.getId());//有值
        System.out.println(user1.getId());

    }

    /*
    如果传入的是一个游离对象 （对象有oid） 满足
    1 在entityManager缓存中没有该对象
    2 在数据库中有对应的记录
    jpa对查询对应的记录 然后返回该记录对应的对象 然后会把游离对象的属性赋值到查询到的对象中
    然后对查询的对象进行update操作
    因此结果会有一条select 一条update
    设置断点可以看到 set方法被调用了两次
     */
    @Test
    public void testMerge3(){
        User user = new User("CC","CC",1);
        user.setId(6);

        User merge = entityManager.merge(user);

        System.out.println(user == merge);

    }

    /*
    如果传入的是一个游离对象 （对象有oid） 满足
    1 在entityManager缓存中没有该对象
    2 在数据库中也没有对应的记录
    jpa会创建一个新的对象 把当前游离对象的属性赋值到新创建的对象中
    对新对象进行持久化insert操作

     */
    @Test
    public void testMerge2(){
        User user = new User("BB","BB",1);
        user.setId(100);

        User merge = entityManager.merge(user);

        //user : 100
        //merge : 6
        System.out.println("user : " + user.getId());//null
        System.out.println("merge : " + merge.getId());//有值

    }

    
    
    /* 
    如果传入的是一个临时对象user
    先创建一个新对象merge(复制操作) 对新对象进行 持久化操作
    因此user没有id而 merge 有id

     */
    @Test
    public void testMerge1(){
        User user = new User("AA","AA",1);

        User merge = entityManager.merge(user);

        System.out.println("user:" + user.getId());//null
        System.out.println("merge:" + merge.getId());//有值

    }

    /*
    只能移除持久化对象 不能移除游离对象
     */
    @Test
    public void testRemove(){
        //移除游离对象报错
//        User user = new User();
//        user.setId(2);
//
//        entityManager.remove(user);

        //可以移除持久化对象
        User user = entityManager.find(User.class, 2);
        entityManager.remove(user);
    }

    /*
    使对象由临时状态变为持久化状态
    如果对象设置了id并且id存在 则会报异常
     */
    @Test
    public void testPersistence(){
        User user = new User();
        user.setUsername("hello");
        user.setPassword("111");
        user.setEmail("hello1@123.com");
        user.setBirth(new Date());
        user.setCreateTime(new Date());
        user.setAge(18);

        // user.setId(1); id存在会报错

        entityManager.persist(user);
        //此时id就会有值
        System.out.println(user.getId());
    }


    /*
    类似乎懒加载
    什么时候用到什么时候调用SQL
    entityManager关闭的情况下 可能会出现懒加载异常
     */
    @Test
    public void testGetReference(){
        User user = entityManager.getReference(User.class, 1);
        System.out.println(user.getClass().getName());
        System.out.println("----------------");
        // entityManager.close(); 会有异常
        System.out.println(user);
    }

    /*
    直接加载并且执行
     */
    @Test
    public void testFind(){
        User user = entityManager.find(User.class, 1);
        System.out.println("----------------");
        System.out.println(user);
    }

}
