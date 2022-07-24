package com.lihd.jpa.junit;

import com.lihd.jpa.pojo.Boy;
import com.lihd.jpa.pojo.Girl;
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
 * @date ：2022/4/21 20:04
 */
public class RelationTest2 {
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
    1.默认情况下, 若获取维护关联关系的一方, 则会通过左外连接获取其关联的对象.
	但可以通过 @OntToOne 的 fetch 属性来修改加载策略.
    默认情况下 是2条语句
    改成懒加载是 1条语句
    */
    @Test
    public void testOneToOneFind2(){
        Girl girl = entityManager.find(Girl.class, 1);
        System.out.println(girl.getGirlName());
        //com.lihd.jpa.pojo.Boy_$$_javassist_2
        System.out.println(girl.getBoy().getClass().getName());
    }
    /*
    默认情况下, 若获取不维护关联关系的一方, 则也会通过左外连接获取其关联的对象.
	可以通过 @OneToOne 的 fetch 属性来修改加载策略. 但依然会再发送 SQL 语句来初始化其关联的对象
	这说明在不维护关联关系的一方, 不建议修改 fetch 属性.

    默认情况下 是1条语句
    改成懒加载是2条语句 因此不建议策略
    */
    @Test
    public void testOneToOneFind1(){
        Boy boy = entityManager.find(Boy.class, 1);
        System.out.println(boy.getBoyName());
        //com.lihd.jpa.pojo.Girl
        System.out.println(boy.getGirl().getClass().getName());
    }

    /*
    先持久化 girl(维护主键) 多出一条update
    先持久化 boy 没有update语句
    双向一对一 关系 建议先 保存不维护关系的一方 （没有外键），这样不会多出 update语句
     */
    @Test
    public void testOneToOnePersist(){
        Boy boy = new Boy();
        Girl girl = new Girl();

        boy.setBoyName("郭靖");
        girl.setGirlName("黄蓉");

        boy.setGirl(girl);
        girl.setBoy(boy);

        entityManager.persist(boy);
        entityManager.persist(girl);

    }


}
