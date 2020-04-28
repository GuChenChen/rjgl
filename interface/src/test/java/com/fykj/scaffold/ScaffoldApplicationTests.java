package com.fykj.scaffold;

import com.fykj.scaffold.security.business.service.impl.XssTagAttributeProtocolServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScaffoldApplicationTests {

    @Autowired
    private XssTagAttributeProtocolServiceImpl xssTagAttributeService;

    @Test
    public void contextLoads() {
        xssTagAttributeService.getTags();
        xssTagAttributeService.getTagAndAttrKey();
        xssTagAttributeService.getTagAndAttrKeyVal();
        xssTagAttributeService.getTagAndKeyAndProtocol();
    }

}
