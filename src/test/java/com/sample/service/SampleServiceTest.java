package com.sample.service;

import com.sample.vo.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

@ContextConfiguration("classpath:/spring/spring-test.xml")
public class SampleServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    SampleService service;

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
        sample = service.add(sample);

        sample.setName("Updated");
        service.update(sample);

        sample = service.get(sample);
        System.out.println(sample);

//        int effectRows = service.delete(sample);
//        Assert.assertEquals(effectRows == 1, true);
    }
}
