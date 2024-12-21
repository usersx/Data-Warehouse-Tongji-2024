package com.mysql.dwbackened.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DirectorActorCountDto {
    private String directorUuid;
    private String actorUuid;
    private int cooperationCount;
}
