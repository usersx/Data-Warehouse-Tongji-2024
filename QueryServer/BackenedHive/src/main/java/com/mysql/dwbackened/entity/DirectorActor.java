package com.mysql.dwbackened.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wyx20
 * @version 1.0
 * @title DirectorActor
 * @description
 * @create 2023/12/27 15:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorActor {
    private String movieId;
    private Integer actorId;
    private Integer directorId;
}
