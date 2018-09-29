package com.sample.dao;

import com.sample.vo.Sample;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

@ContextConfiguration("classpath:/spring/spring-test.xml")
public class SampleDaoTest extends AbstractTestNGSpringContextTests {
    @Autowired
    SampleDao dao;

    @Test
    public void test() throws Exception {
        Sample sample = new Sample();
        sample.setName("Test");
        sample.setType("Type");
        sample.setCollectDate(new Date());
        sample.setProvince("Province");
        sample.setCity("City");
        sample.setCounty("County");
        sample.setCategory("Category");
        sample.setComment("Comment");
        sample.setQuantity(0);
        sample.setLocation("Location");
        sample.setCreatePerson("Admin");
        sample.setCreateTime(new Date());
        sample = dao.add(sample);

        sample.setName("Updated");
        dao.update(sample);

        sample = dao.get(sample);
        System.out.println(sample);

        int effectRows = dao.delete(sample);
        Assert.assertEquals(effectRows == 1, true);
    }
}
