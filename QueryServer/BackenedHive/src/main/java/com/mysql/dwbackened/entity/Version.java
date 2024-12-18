package com.mysql.dwbackened.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wyx20
 * @version 1.0
 * @title Version
 * @description
 * @create 2023/12/25 14:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Version {
    private String versionUuid;
    private String movieId;
    private String edition;
    private String language;
}
