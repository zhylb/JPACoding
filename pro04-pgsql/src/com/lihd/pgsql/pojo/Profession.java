package com.lihd.pgsql.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/22 22:00
 */
@Entity
public class Profession {

    private String spno;
    private String pname;

    @Id
    public String getSpno() {
        return spno;
    }

    public void setSpno(String spno) {
        this.spno = spno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
