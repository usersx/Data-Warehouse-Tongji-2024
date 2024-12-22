package com.mysql.dwbackened.controller;

import com.mysql.dwbackened.service.DateService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/hive/date")
public class DateController {
      @Autowired
      private DateService dateService;

      @GetMapping("/year")
//      @ApiOperation(notes = "", value = "根据年份查询统计")
      public HashMap<String,Object> findByYear(@RequestParam String year){
            return dateService.findByYear(year);
      }

}
