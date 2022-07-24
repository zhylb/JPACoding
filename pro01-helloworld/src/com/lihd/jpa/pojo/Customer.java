package com.lihd.jpa.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 14:20
 */

@Entity
@Table(name = "t_customer")
public class Customer {
    private Integer id;

    private Date createDate;
    private Date lastModifyDate;
    private String lastName;
    private String email;
    private Integer age;

    private Set<Order> orders = new HashSet<>();



    public Customer() {
    }

    public Customer(String lastName, String email, Integer age) {
        this.lastName = lastName;
        this.email = email;
        this.age = age;

        this.createDate = new Date();
        this.lastModifyDate = new Date();

    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "last_modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    //映射单向一对多
    //使用@OneToMany来映射 1-n的关联关系
    //使用 @JoinColumn 来映射外键列的名称
    //可以修改删除策略 修改@OneToMany的cascade属性修改默认的删除策略
    //可以使用 @OneToMany的fetch属性修改默认的加载策略
    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE},mappedBy = "customer")
    //@JoinColumn(name = "customer_id")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
