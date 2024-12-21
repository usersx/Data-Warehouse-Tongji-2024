package com.mysql.dwbackened.controller;

import com.mysql.dwbackened.dto.RelationSearchDto;
import com.mysql.dwbackened.service.ActorService;
import com.mysql.dwbackened.service.DirectorService;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/hive/relation")
public class RelationController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private DirectorService directorService;

    @GetMapping("/count")
//    @ApiOperation(notes="",value="获取关系查询的结果总数")
    public HashMap<String,Object> getActorRelationCount(@RequestBody RelationSearchDto relationSearchDto){
        if(relationSearchDto.getSource().equals("actor")){
            return actorService.selectActorRelationCount(relationSearchDto.getPage(), relationSearchDto.getPer_page());
        }
        else if(relationSearchDto.getSource().equals("director")){
            return directorService.selectDirectorActorCount(relationSearchDto.getPage(),relationSearchDto.getPer_page());
        }
        return null;
    }

    @PostMapping("/detail")
//    @ApiOperation(notes="",value="获取关系查询的结果具体信息")
    public HashMap<String,Object> getActorRelationInfo(@RequestBody RelationSearchDto relationSearchDto){
        if(relationSearchDto.getSource().equals("actor")){
            return actorService.selectActorRelationInfo(relationSearchDto.getPage(), relationSearchDto.getPer_page());
        }
        else if(relationSearchDto.getSource().equals("director")){
            return directorService.selectDirectorActorInfo(relationSearchDto.getPage(), relationSearchDto.getPer_page());
        }
        return null;
    }

    @GetMapping("/popular")
//    @ApiOperation(notes="",value="获取某类型电影最受关注演员组合")
    public HashMap<String,Object> getGenreRelationInfo(@RequestParam String genre){
        return actorService.getGenreRelationInfo(genre);
    }

}
