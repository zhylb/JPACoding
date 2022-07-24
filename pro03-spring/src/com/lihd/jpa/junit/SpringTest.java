package com.lihd.jpa.junit;

import com.lihd.jpa.pojo.Person;
import com.lihd.jpa.service.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/21 22:32
 */
public class SpringTest {
    private ApplicationContext ctx = null;
    private PersonService personService = null;

    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        personService = ctx.getBean(PersonService.class);
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void test01(){
        Person p1 = new Person("Spring","Spirng@spring.org",18);
        Person p2 = new Person("JPA","JPA@jpa.org",14);
        personService.addPerson(p1, p2);

    }


}
