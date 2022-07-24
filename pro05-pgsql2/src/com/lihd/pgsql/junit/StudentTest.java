package com.lihd.pgsql.junit;

import com.lihd.pgsql.dao.ProfessionDAO;
import com.lihd.pgsql.dao.StudentDAO;
import com.lihd.pgsql.pojo.Profession;
import com.lihd.pgsql.pojo.Student;
import com.lihd.pgsql.util.RandomValue;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/23 13:30
 */
public class StudentTest {
    ProfessionDAO professionDAO = new ProfessionDAO();
    StudentDAO studentDAO = new StudentDAO();

    @Test
    public void insertBatch(){

        int insertNum = 50;

        List<Profession> professions = professionDAO.getProfessions();
        String[] randomStringArray = RandomValue.getRandomStringArray(10, insertNum);

        ArrayList<Student> studentArrayList = new ArrayList<>();

        for (int i = 0; i < insertNum; i++) {
            String sno = randomStringArray[i];
            String sex = RandomValue.getSex();
            String sname = RandomValue.getChineseName(sex);
            String s_native = RandomValue.getProCity();


            Date birthday = RandomValue.randomDate("1980-01-01", "2005-01-01");

            String dno = RandomValue.getDno();
            Profession spno = professionDAO.getRandom(professions);
            Date entime = RandomValue.randomDate("2005-01-01", "2020-07-01");

            String home = RandomValue.getRoad();

            String tel = RandomValue.getTel();

            Student student = new Student(sno, sname, sex, s_native, birthday, dno, spno, entime, home, tel);

            studentArrayList.add(student);


        }
        studentDAO.addProfessionDAO(studentArrayList);

        studentDAO.destroy();

    }


    @Test
    public void getAllStu(){
        List<Student> students = studentDAO.getProfessions();

        for (Student student : students) {
            System.out.println(student);
        }

        studentDAO.destroy();
    }









}
