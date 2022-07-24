package com.lihd.jpa.junit;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 9:43
 */
public class APITest {
    /**
     * Test01 : Persistence
     * 主要来用来获取 EntityManagerFactory
     * 两个方法(重载)
     * createEntityManagerFactory()
     *
     * 其实和spring整合之后 甚至不需要 使用这个方法 第二个重载方法了解即可
     *
     */
    @Test
    public void test01 (){
        //这里主要了解 第二个方法即可 可以实现对之前参数的覆盖
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-pro01");
        HashMap<String, Object> map = new HashMap<>();
        map.put("hibernate.show_sql",false);
        EntityManagerFactory factory1 = Persistence.createEntityManagerFactory("jpa-pro01", map);

    }


    /**
     * Test02 : EntityManagerFactory
     * createEntityManager()：用于创建实体管理器对象实例。
     * createEntityManager(Map map)：用于创建实体管理器对象实例的重载方法，Map 参数用于提供 EntityManager 的属性。
     * isOpen()：检查 EntityManagerFactory 是否处于打开状态。实体管理器工厂创建后一直处于打开状态，除非调用close()方法将其关闭。
     * close()：关闭 EntityManagerFactory 。 EntityManagerFactory 关闭后将释放所有资源，isOpen()方法测试将返回 false，其它方法将不能调用，否则将导致IllegalStateException异常。
     *
     * 最主要的方法还是 create 和 close();
     */
    @Test
    public void test02 (){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-pro01");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.close();
        factory.close();
    }

    /**
     * Test03 : EntityTransaction
     * 接口用来管理资源层实体管理器的事务操作。通过调用实体管理器的getTransaction方法 获得其实例。
     * begin ()
     * 用于启动一个事务，此后的多个数据库操作将作为整体被提交或撤消。若这时事务已启动则会抛出 IllegalStateException 异常。
     * commit ()
     * 用于提交当前事务。即将事务启动以后的所有数据库更新操作持久化至数据库中。
     * rollback ()
     * 撤消(回滚)当前事务。即撤消事务启动后的所有数据库更新操作，从而不对数据库产生影响。
     * setRollbackOnly ()
     * 使当前事务只能被撤消。
     * getRollbackOnly ()
     * 查看当前事务是否设置了只能撤消标志
     * isActive ()
     * 查看当前事务是否是活动的。如果返回true则不能调用begin方法，否则将抛出 IllegalStateException 异常；如果返回 false 则不能调用 commit、rollback、setRollbackOnly 及 getRollbackOnly 方法，否则将抛出 IllegalStateException 异常。
     *
     */
    @Test
    public void test03 (){
        //掌握 begin commit rollback  即可
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-pro01");
        EntityManager entityManager = factory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }

        entityManager.close();
        factory.close();
    }
}
