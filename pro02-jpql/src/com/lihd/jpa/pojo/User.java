package com.lihd.jpa.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * 六个基本注解
 * 1 Entity 指出该表是一个实体 ， 将类映射到表
 * 2 table 去映射表的表名 使用name属性 不写的话表名就是类名
 * 3 Id 将一个属性映射到 主键 ， 建议放到get方法上 似乎可以放到任意位置
 * 4 GeneratedValue 生成主键的策略 默认的值是AUTO如果是AUTO可以不写默认值
 * 5 Basic 将一个简单属性映射到数据表中 ，不写默认就是@Basic
 * 6 Column 实体属性和表的字段名字不同时使用，可以设置其他约束
 *
 * 注意 ： 对于基本属性的方法 必须有get和set方法 否则报错
 * 其他注解
 * transient 说明这个属性不需要映射到数据库
 *
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/20 15:46
 */
@NamedQuery(name = "testNamedQuery",query = "from User u where u.id = ?")
@Cacheable(true)
@Entity
@Table(name = "t_user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String email;

    private Date birth;
    private Date createTime;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "user_name")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "pass_word")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    @Column(name = "t_email",length = 50,unique = true,nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Transient
    public String getInfo(){
        //如果没有set  Could not find a setter for property info in class com.lihd.jpa.pojo.User
        return "username : " + username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                ", createTime=" + createTime +
                '}';
    }

    public User() {
    }

    public User(Integer age) {
        this.age = age;
    }

    public User(String username, String password, Integer age) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.birth = new Date();
        this.createTime = new Date();
        this.email = UUID.randomUUID().toString() + "@123.com";
    }
}
