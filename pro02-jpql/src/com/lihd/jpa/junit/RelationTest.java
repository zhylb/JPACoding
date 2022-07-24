package com.lihd.jpa.junit;

import com.lihd.jpa.pojo.Category;
import com.lihd.jpa.pojo.Item;
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
 * @date ：2022/4/22 8:58
 */
public class RelationTest {
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void init(){
        factory = Persistence.createEntityManagerFactory("jpa-pro02");
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
    由于维护数据的不是一方 而是一个中间表
    因此区别与双向一对一 这种方式两个表是没有区别的

    可以理解为两者的地位是平等的
     */

    @Test
    public void testManyToManyFind2(){
        Category category = entityManager.find(Category.class, 1);

        System.out.println(category.getCategoryName());
        System.out.println(category.getItems());
    }

    @Test
    public void testManyToManyFind1(){
        Item item = entityManager.find(Item.class, 1);

        System.out.println(item.getItemName());

        System.out.println(item.getCategories());
    }


    /*
    无论先持久化 维护关系的表 还是不是 结果都是 8条数据

     */
    @Test
    public void testManyToManyPersist(){

        Item item1 = new Item("吕布");
        Item item2 = new Item("关羽");

        Category category1 = new Category("猛将");
        Category category2 = new Category("骁勇");

        item1.getCategories().add(category1);
        item1.getCategories().add(category2);

        item2.getCategories().add(category1);
        item2.getCategories().add(category2);

        category1.getItems().add(item1);
        category1.getItems().add(item2);

        category2.getItems().add(item1);
        category2.getItems().add(item2);


        entityManager.persist(category1);
        entityManager.persist(category2);

        entityManager.persist(item1);
        entityManager.persist(item2);

    }


}
