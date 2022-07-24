package com.lihd.jpa.pojo;

import org.junit.Test;

import javax.persistence.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 19:56
 */

@Entity
@Table(name = "t_boy")
public class Boy {
    private Integer id;
    private String boyName;
    private Girl girl;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "boy_name")
    public String getBoyName() {
        return boyName;
    }

    public void setBoyName(String boyName) {
        this.boyName = boyName;
    }

    //对于不维护关联关系, 没有外键的一方, 使用 @OneToOne 来进行映射, 建议设置 mappedBy=类名
    @OneToOne(mappedBy = "boy")//,fetch = FetchType.LAZY不建议修改
    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }
}
