package com.lihd.pgsql.dao;



import com.lihd.pgsql.pojo.Profession;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/22 23:28
 */
public class BaseDAO<T> {
    protected Class geneClass;

    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();

        ParameterizedType type = (ParameterizedType) genericSuperclass;

        Type[] actualTypeArguments = type.getActualTypeArguments();

        geneClass = (Class) actualTypeArguments[0];

    }


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

    public void addProfessionDAO(List<T> list){
        for (T t : list) {
            entityManager.persist(t);
        }
    }

    public List<T> getProfessions(){
        String name = geneClass.getSimpleName();
        System.out.println(name);

        String jpql = "from " +name;
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }


}
