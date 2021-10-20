package com.github.xucux.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
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

    @NacosInjected
    private NamingService namingService;


    private RestTemplate restTemplate = new RestTemplate();


    /**
     * 获取学生信息
     * @param id
     * @return
     */
    @GetMapping("/info/{id}")
    public Object getStudentInfo(@PathVariable("id")Integer id){
        Map<String,Object> info = new HashMap<>();
        info.put("id",id);
        info.put("info",queryStudentInfo(id));

        return info;
    }

    private JSONObject queryStudentInfo(Integer id) {
        try {
            if (namingService != null) {
                // 选择user_service服务的一个健康的实例（可配置负载均衡策略）
                Instance instance = namingService.selectOneHealthyInstance("nacos-producer-service");
                // 拼接请求接口url并请求选取的实例
                String url = "http://" + instance.getIp() + ":" + instance.getPort() + "/demo/student/detail/"+id;
                ResponseEntity<JSONObject> entity = restTemplate.getForEntity(url, JSONObject.class);
                return entity.getBody();
            }
        } catch (Exception e) {
            log.error("查询学生数据失败", e);
        }
        return null;
    }

}
