package com.lihd.pgsql.junit;


import com.lihd.pgsql.dao.ProfessionDAO;
import com.lihd.pgsql.pojo.Profession;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/22 23:20
 */
public class ProfessionTest {
    ProfessionDAO professionDAO = new ProfessionDAO();

    @Test
    public void getALl(){
        List<Profession> professions = professionDAO.getProfessions();

        for (Profession profession : professions) {
            System.out.println(profession);
        }

        int size = professions.size();

        System.out.println(size);

        professionDAO.destroy();


    }


    @Test
    public void addData() throws Exception{

        //professionDAO.addProfessionDAO(null);


        ArrayList<Profession> professions = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/file/专业1.txt"));
        String line;
        while((line = br.readLine()) != null){
            String[] split = line.split(" ");
            Profession profession = new Profession(split[0],split[1]);
            professions.add(profession);
        }

        professionDAO.addProfessionDAO(professions);
        br.close();
        professionDAO.destroy();

    }

}
