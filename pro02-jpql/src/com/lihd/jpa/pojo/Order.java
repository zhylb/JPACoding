package com.lihd.jpa.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 14:20
 */
@Entity
@Table(name = "t_order")
public class Order {
    private Integer id;
    private String orderName;
    private Date orderDate;
    private Customer customer;

    public Order() {
    }

    public Order(String orderName) {
        this.orderName = orderName;
        this.orderDate = new Date();
    }

    public Order(String orderName, Customer customer) {
        this.orderName = orderName;
//        this.customer = customer;
        this.orderDate = new Date();
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @ManyToOne()
    //@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
