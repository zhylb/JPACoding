package com.lihd.jpa.junit;

import com.lihd.jpa.pojo.Customer;
import com.lihd.jpa.pojo.Order;
import net.sf.ehcache.search.expression.Or;
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
 * @date ：2022/4/21 14:30
 */
public class RelationTest {
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
    双向 1-n的关系 执行保存时
    先保存n的一段 在保存1的一段 默认情况下会多出2n条update语句 -- 使用set方法
    （但是我这种情况下 是多出n条update语句 我把他放到构造器的原因） -- 使用构造器
    先保存1的一段 再保存n的一段 时 多出n条update语句

    在进行双向一对多的关联关系时 建议使用N的一方来维护关联关系 而1的一方不维护关联关系 这样会有效的减少sql语句
    （比如一个国家的领导人记不住所有公民的名字 ，但是所有公民可以记住领导人的名字）
    注意若在1的一段中的@OneToMany使用mapperBy属性 则这一段就不能再使用@JoinColum属性
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "customer")
    //@JoinColumn(name = "customer_id")

    上面的意思是Order.customer 帮我映射

     */
    @Test
    public void testOneToManyManyToOnePersist() {

        Customer libai = new Customer("libai", "libai@DaTang.com", 18);


//        Order aa = new Order("AA",libai);
//        Order bb = new Order("BB",libai);
//        Order cc = new Order("CC",libai);

        Order aa = new Order("AA");
        Order bb = new Order("BB");
        Order cc = new Order("CC");

        libai.getOrders().add(aa);
        libai.getOrders().add(bb);
        libai.getOrders().add(cc);

        aa.setCustomer(libai);
        bb.setCustomer(libai);
        cc.setCustomer(libai);



        //执行保存操作
        //调换顺序也没有关系 但是会多出近一倍的语句

        entityManager.persist(libai);
        entityManager.persist(aa);
        entityManager.persist(bb);
        entityManager.persist(cc);

    }




    /*
    测试多对一的修改
    */
    @Test
    public void testOneToManyUpdate(){

        Customer customer = entityManager.find(Customer.class,5);

        customer.getOrders().iterator().next().setOrderName("浮青云剑");

    }

    /*
    默认情况下 若删除1的一段 则会先把关联n的一段的外键置空 然后进行删除
    可以修改删除策略 修改@OneToMany的cascade属性修改默认的删除策略
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE})
     */
    @Test
    public void testOneToManyRemove(){

        Customer customer = entityManager.find(Customer.class, 4);
        entityManager.remove(customer);

    }

    /*
    默认对关联的多的一方使用懒加载的策略
    可以使用 @OneToMany的fetch属性修改默认的加载策略
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
     */
    @Test
    public void testOneToManyFind(){
        Customer customer = entityManager.find(Customer.class, 2);

        System.out.println(customer.getLastName());

        for (Order order : customer.getOrders()) {
            System.out.println(order);
        }


    }


    /*
    单向一对多保存
     由于是 1的一段维护关联关系 导致插入order（多的一端）时没有外键数据 因此一定会有update
     */
    @Test
    public void testOneToManyPersist() {

        Customer libai = new Customer("libai", "libai@DaTang.com", 18);


        Order aa = new Order("AA");
        Order bb = new Order("BB");
        Order cc = new Order("CC");

        libai.getOrders().add(aa);
        libai.getOrders().add(bb);
        libai.getOrders().add(cc);

        //执行保存操作
        //调换顺序也没有关系 但是会多出近一倍的语句

        entityManager.persist(libai);
        entityManager.persist(aa);
        entityManager.persist(bb);
        entityManager.persist(cc);

    }
    //************************************8

    @Test
    public void test1(){

    }

    /*
    测试多对一的修改
    */
    @Test
    public void testManyToOneUpdate(){
        Order order = entityManager.find(Order.class, 1);
//        order.getCustomer().setLastName("李太白");
    }

    /*
    不能直接删除 1 的一端 因为有外键约 束
     */
    @Test
    public void testManyToOneRemove(){

        Customer customer = entityManager.find(Customer.class, 1);
        entityManager.remove(customer);

    }

    /*
    默认情况下 使用左外连接 left out join
    获取n的一段的对象 和 其关联的1的一段的对象

    能够改为懒加载吗 可以使用ManyToOne的Fetch属性 修改默认关联属性的加载策略
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")

     */
    @Test
    public void testManyToOneFind(){
        Order order = entityManager.find(Order.class, 1);
        System.out.println(order.getOrderName());

//        System.out.println(order.getCustomer().getLastName());


    }


    /*
    保存多对一时 先保存一 然后保存n这样 就不会多出来n条update语句
    能够改为懒加载吗 可以使用ManyToOne的Fetch属性 修改默认关联属性的加载策略

     */
    @Test
    public void testManyToOnePersist() {

        Customer libai = new Customer("libai", "libai@DaTang.com", 18);


        Order aa = new Order("AA", libai);
        Order bb = new Order("BB", libai);
        Order cc = new Order("CC", libai);

        //执行保存操作
        //调换顺序也没有关系 但是会多出近一倍的语句

        entityManager.persist(libai);
        entityManager.persist(aa);
        entityManager.persist(bb);
        entityManager.persist(cc);

    }




}
