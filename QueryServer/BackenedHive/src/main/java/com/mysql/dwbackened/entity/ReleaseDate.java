package com.mysql.dwbackened.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wyx20
 * @version 1.0
 * @title releaseDate
 * @description
 * @create 2023/12/25 8:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "releasedate")
public class ReleaseDate {
    @TableId
    private Integer dateId;
    private String movieId;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer weekday;
}
