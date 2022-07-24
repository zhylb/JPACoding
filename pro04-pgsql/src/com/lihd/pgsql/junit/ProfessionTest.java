package com.lihd.pgsql.junit;

import com.lihd.pgsql.dao.ProfessionDAO;
import com.lihd.pgsql.pojo.Profession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/22 23:20
 */
public class ProfessionTest {
    @Test
    public void addData() throws Exception{
        ProfessionDAO professionDAO = new ProfessionDAO();
        //professionDAO.addProfessionDAO(null);

        BufferedReader br = new BufferedReader(new FileReader("src/file/专业1.txt"));
        String line;
        while((line = br.readLine()) != null){
            String[] split = line.split("(\\w+)\\s+(.*)");
            System.out.println(Arrays.toString(split));
        }

        br.close();



        professionDAO.destroy();

    }

}
