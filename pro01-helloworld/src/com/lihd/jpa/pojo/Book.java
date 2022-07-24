package com.lihd.jpa.pojo;

import javax.persistence.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 9:21
 */
@Entity
@Table(name = "t_book")
public class Book {
    private Integer id;
    private String bookName;
    private String author;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ID_generator")
    @TableGenerator(name = "ID_generator",//和上面的generator值对应
            table = "t_id_generator",//找到生成主键的表
            pkColumnName = "pk_name",//找到生成主键表的生成字段的列
            pkColumnValue = "t_book_id",//找到该字段对应的值 从而确定某一行
            valueColumnName = "pk_value",//找到这一行 对应的值
            allocationSize = 10//每次增加的大小默认是50
    )
    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "book_name")
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
