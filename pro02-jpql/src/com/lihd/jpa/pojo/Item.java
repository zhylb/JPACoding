package com.lihd.jpa.pojo;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/22 8:47
 */

@Entity
@Table(name = "t_item")
public class Item {

    private Integer id;

    private String itemName;

    private Set<Category> categories = new HashSet<>();

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "item_name")
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    //使用 @ManyToMany 注解来映射多对多关联关系
    //使用 @JoinTable 来映射中间表
    //1. name 指向中间表的名字
    //2. joinColumns 映射当前类所在的表在中间表中的外键
    //2.1 name 指定外键列的列名
    //2.2 referencedColumnName 指定外键列关联当前表的哪一列
    //3. inverseJoinColumns 映射关联的类所在中间表的外键
    @JoinTable(name = "t_item_category",
            joinColumns = {@JoinColumn(name = "item_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id",referencedColumnName = "id")}
    )
    @ManyToMany
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }
}
