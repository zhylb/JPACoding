package com.lihd.jpa.junit;

import com.lihd.jpa.pojo.Customer;
import com.lihd.jpa.pojo.Order;
import com.lihd.jpa.pojo.User;
import org.hibernate.ejb.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 21:09
 */
public class JPQLTest {

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


    @Test
    public void testDelete(){
        String jpql = "delete from Book b where b.id = ?";
        Query query = entityManager.createQuery(jpql).setParameter(1, 100);
        query.executeUpdate();

    }

    @Test
    public void testUpdate(){
        String jpql = "update User u set u.username = ? where u.id = ?";
        Query query = entityManager.createQuery(jpql).setParameter(1, "韩信").setParameter(2, 8);

        query.executeUpdate();


    }


    @Test
    public void testJpqlFunction(){
        String jpql = "select upper(u.email) from User u";
        List resultList = entityManager.createQuery(jpql).getResultList();
        System.out.println(resultList);
    }


    @Test
    public void testSubQuery(){
        String jpql = "select o from Order o where o.customer = (select c from Customer c where c.lastName = ?)";

        List<Order> libai1 = entityManager.createQuery(jpql).setParameter(1, "libai1").getResultList();

        System.out.println(libai1);

    }


    //1 条操作实现
    @Test
    public void testLeftOuterJoinFetch2(){
        String jpql = "from Customer c left outer join fetch c.orders where c.id = ?";
        Customer customer = (Customer) entityManager.createQuery(jpql).setParameter(1, 5).getSingleResult();

        System.out.println(customer.getLastName());

        System.out.println(customer.getOrders());

    }

    //2 两条操作实现
    @Test
    public void testLeftOuterJoinFetch1(){
        String jpql = "from Customer c  where c.id = ?";
        Customer customer = (Customer) entityManager.createQuery(jpql).setParameter(1, 5).getSingleResult();

        System.out.println(customer.getLastName());

        System.out.println(customer.getOrders());

    }


    @Test
    public void testGroupBy(){
        String jpql = "select o.customer from Order o group by o.customer having count(*) > 3";

        List<Customer> list = entityManager.createQuery(jpql).getResultList();

        System.out.println(list);

    }


    @Test
    public void testOrderBy(){
        String jpql = "from User u where id > ? order by age desc";

        List<User> resultList = entityManager.createQuery(jpql).setParameter(1, 1).getResultList();

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
    public void testQueryCache(){
        String jpql = "from User u where u.age > ?";
        //因为有了缓存 因此只有一条查询结果
        Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
        // query = entityManager.createQuery(jpql); 取消注释将会是两条查询


        query.setParameter(1,1);

        List<User> resultList = query.getResultList();

        System.out.println(resultList);
        query.setParameter(1,1);

        resultList = query.getResultList();

        System.out.println(resultList);
    }

    @Test
    public void testNativeQuery(){
        String sql = "select age from t_user where id = ?";
        Query query = entityManager.createNativeQuery(sql).setParameter(1, 1);

        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);
    }

    @Test
    public void testNamedQuery(){
        Query testNamedQuery = entityManager.createNamedQuery("testNamedQuery").setParameter(1, 1);

        User result = (User) testNamedQuery.getSingleResult();

        System.out.println(result);
    }
    //默认情况下, 若只查询部分属性, 则将返回 Object[] 类型的结果. 或者 Object[] 类型的 List.
    //也可以在实体类中创建对应的构造器, 然后再 JPQL 语句中利用对应的构造器返回实体类的对象.
    @Test
    public void testPartlyProperties(){
        String jpql = "select new User(u.username,u.password,u.age) from User u where u.id > ?";

        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,2);

        List<User> resultList = query.getResultList();

        System.out.println(resultList);

    }
    @Test
    public void testHelloJPQL(){
        String jpql = "FROM User u WHERE u.id > ?";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1,1);
        List<User> resultList = query.getResultList();
        System.out.println(resultList);
    }
}
