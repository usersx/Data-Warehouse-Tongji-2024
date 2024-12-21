package com.mysql.dwbackened.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActorRelationCountDto {
    private String leftPersonId;
    private String rightPersonId;
    private int cooperationCount;
}
