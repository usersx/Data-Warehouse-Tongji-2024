package com.mysql.dwbackened.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
