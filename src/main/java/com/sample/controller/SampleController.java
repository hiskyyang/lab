package com.sample.controller;

import com.basic.cache.service.CacheManager;
import com.code.vo.Code;
import com.sample.service.SampleService;
import com.sample.vo.Sample;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("sample")
public class SampleController {
    @Autowired
    SampleService sampleService;
    @Autowired
    CacheManager cacheManager;

    @RequestMapping(value = "sample", method = {RequestMethod.GET})
    public ModelAndView sample(Sample sample) {
        ModelAndView mav = new ModelAndView();

        setCode(mav, sample);

        mav.setViewName("sample/sample");
        return mav;
    }

    private void setCode(ModelAndView mav, Sample sample) {
        mav.addObject("types", cacheManager.getCodes("type"));
        mav.addObject("locations", cacheManager.getCodes("location"));
        mav.addObject("categorys", cacheManager.getCodes("category"));
        mav.addObject("provinces", cacheManager.getCodes("province"));

        if (sample.getSampleId() != null) {
            sample = sampleService.get(sample);
            mav.addObject("sample", sample);

            if (StringUtils.isNotEmpty(sample.getProvince())) {
                List<Code> citys = cacheManager.getCodes("city", sample.getProvince());
                mav.addObject("citys", citys);
            }

            if (StringUtils.isNotEmpty(sample.getCity())) {
                List<Code> countys = cacheManager.getCodes("county", sample.getCity());
                mav.addObject("countys", countys);
            }
        }
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    @ResponseBody
    public int add(Sample sample) {
        try {
            sampleService.add(sample);
            return sample.getSampleId();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public int delete(Sample sample) {
        try {
            return sampleService.delete(sample);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public int update(Sample sample) {
        try {
            sampleService.update(sample);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @RequestMapping("list")
    public ModelAndView list(Sample sample) {
        ModelAndView mav = new ModelAndView();

        mav.addObject("types", cacheManager.getCodes("type"));
        mav.addObject("locations", cacheManager.getCodes("location"));
        mav.addObject("categorys", cacheManager.getCodes("category"));
        mav.addObject("provinces", cacheManager.getCodes("province"));

        List<Sample> samples = sampleService.list(sample);
        format(samples);
        mav.addObject("samples", samples);
        mav.addObject("sample", sample);
        mav.setViewName("sample/list");
        return mav;
    }

    private void format(List<Sample> list) {
        Map<String, String> typeMap = toMap(cacheManager.getCodes("type"));
        Map<String, String> locationMap = toMap(cacheManager.getCodes("location"));
        Map<String, String> categoryMap = toMap(cacheManager.getCodes("category"));

        Code param = new Code();
        param.setType("province");
        Map<String, String> provinceMap = toMap(cacheManager.getCodes("province"));
        param.setType("city");
        Map<String, String> cityMap = toMap(cacheManager.getCodes("city"));
        param.setType("county");
        Map<String, String> countyMap = toMap(cacheManager.getCodes("county"));
        for (Sample sample : list) {
            sample.setType(typeMap.get(sample.getType()));
            sample.setLocation(locationMap.get(sample.getLocation()));
            sample.setCategory(categoryMap.get(sample.getCategory()));
            sample.setProvince(provinceMap.get(sample.getProvince()));
            sample.setCity(cityMap.get(sample.getCity()));
            sample.setCounty(countyMap.get(sample.getCounty()));
        }
    }

    private Map<String, String> toMap(List<Code> list) {
        return list.stream().collect(Collectors.toMap(Code::getCode, Code::getValue));
    }

    @InitBinder
    public void initBinder(ServletRequestDataBinder bin) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor cust = new CustomDateEditor(sdf, true);
        bin.registerCustomEditor(Date.class, cust);
    }
}
