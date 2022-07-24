package com.lihd.jpa.pojo;

import javax.persistence.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 19:56
 */
@Entity
@Table(name = "t_girl")
public class Girl {

    private Integer id;
    private String girlName;
    private Boy boy;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "girl_name")
    public String getGirlName() {
        return girlName;
    }

    public void setGirlName(String girlName) {
        this.girlName = girlName;
    }
    //使用 @OneToOne 来映射 1-1 关联关系。
    //若需要在当前数据表中添加主键则需要使用 @JoinColumn 来进行映射. 注意, 1-1 关联关系, 所以需要添加 unique=true
    @OneToOne(fetch = FetchType.LAZY)//修改为懒加载
    @JoinColumn(name = "boy_id",unique = true,referencedColumnName = "id")
    public Boy getBoy() {
        return boy;
    }

    public void setBoy(Boy boy) {
        this.boy = boy;
    }
}
