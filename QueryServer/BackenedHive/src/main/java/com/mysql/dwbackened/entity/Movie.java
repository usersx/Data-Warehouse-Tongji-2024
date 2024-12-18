package com.mysql.dwbackened.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wyx20
 * @version 1.0
 * @title movie
 * @description
 * @create 2023/12/25 8:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @TableId
    private String movieId;
    private String movieTitle;
    private Float imdbScore;
    private Integer reviewNum;
}
