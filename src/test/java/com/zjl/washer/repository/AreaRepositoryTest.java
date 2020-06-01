package com.zjl.washer.repository;

import com.zjl.washer.DAO.Area;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AreaRepositoryTest{

    @Autowired
    private AreaRepository repository;

    @Test
    public void findOneTest(){
        Area area = repository.findOne(1);
        System.out.println(area.toString());
    }

    @Test
    public void saveTest(){
        Area area = new Area("san","di","123");
        Area result = repository.save(area);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByAreaIdIn(){
        List<Integer> list = Arrays.asList(1,2,3);
        List<Area> result = repository.findByAreaIdIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}