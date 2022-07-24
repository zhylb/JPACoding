package com.lihd.jpa.service;

import com.lihd.jpa.dao.PersonDAO;
import com.lihd.jpa.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 22:43
 */
@Service
public class PersonService {

    @Autowired
    private PersonDAO personDAO;

    @Transactional
    public void addPerson(Person p1,Person p2){
        personDAO.addPerson(p1);
//        int a = 1/0;
        personDAO.addPerson(p2);
    }

}
