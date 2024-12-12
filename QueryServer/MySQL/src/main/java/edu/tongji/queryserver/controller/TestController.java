package edu.tongji.queryserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/test-db")
    public String testConnection() {
        try {
            // 测试数据库连接
            jdbcTemplate.execute("SELECT 1");
            return "数据库连接成功！";
        } catch (Exception e) {
            // 返回详细的错误信息
            return "数据库连接失败：" + e.getMessage() + "\n堆栈跟踪：" + e.getStackTrace().toString();
        }
    }

    @GetMapping("/test-table")
    public String testTable() {
        try {
            // 测试查询具体的表
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Movie", Integer.class);
            return "Movie表查询成功，共有记录：" + count;
        } catch (Exception e) {
            return "表查询失败：" + e.getMessage() + "\n堆栈跟踪：" + e.getStackTrace().toString();
        }
    }
} 