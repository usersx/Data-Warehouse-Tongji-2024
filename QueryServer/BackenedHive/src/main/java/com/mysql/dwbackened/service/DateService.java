package com.mysql.dwbackened.service;

import java.util.HashMap;


public interface DateService {
    HashMap<String,Object> findByYear(String year);
}
