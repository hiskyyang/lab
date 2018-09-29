package com.sample.service;

import com.basic.service.BaseService;
import com.sample.vo.Sample;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SampleService extends BaseService<Sample> {
    Logger log = Logger.getLogger(this.getClass());

//    @Autowired
//    @Qualifier("sampleDao")
//    BaseDao<Sample> baseDao;
}
