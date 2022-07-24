package com.lihd.jpa.dao;

import com.lihd.jpa.pojo.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 22:38
 */
@Repository
public class PersonDAO {

    //如何获取到和当前事务关联的 EntityManager 对象呢 ?
    //通过 @PersistenceContext 注解来标记成员变量!
    @PersistenceContext
    private EntityManager entityManager;

    public void addPerson(Person person){
        entityManager.persist(person);
    }
}
