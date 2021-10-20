package com.github.xucux.controller;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @descriptions: 获取学生信息
 * @author: xucl
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    /**
     * 获取学生信息
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public Object getStudentInfo(@PathVariable("id")Integer id){
        Map<String,Object> info = new HashMap<>();
        info.put("id",id);
        info.put("userName","test");
        info.put("nickName","小明");
        return info;
    }


}
