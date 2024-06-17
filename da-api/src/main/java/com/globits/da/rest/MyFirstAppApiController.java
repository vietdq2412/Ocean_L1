package com.globits.da.rest;

import com.globits.da.dto.myFirstDto.MyFirstDto;
import com.globits.da.service.impl.MyFirstApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/myFirstApi")
public class MyFirstAppApiController {
    @Autowired
    private MyFirstApiService myFirstApiService;

    @RequestMapping(method = RequestMethod.GET,value = "/firstApi")
    public String firstApi() {
        return myFirstApiService.FirstApi();
    }

    @RequestMapping(method = RequestMethod.POST,value = "/firstApi")
    public String secondApi(@RequestBody MyFirstDto myFirstDto) {
        return myFirstDto.toString();
    }
}
