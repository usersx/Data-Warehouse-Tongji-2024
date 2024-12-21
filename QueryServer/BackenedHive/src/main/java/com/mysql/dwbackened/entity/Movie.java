package com.mysql.dwbackened.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
