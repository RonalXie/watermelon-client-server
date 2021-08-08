package com.xsy.controller;

import com.xsy.entity.CommonResult;
import com.xsy.entity.RedisPrefix;
import com.xsy.entity.user.Played;
import com.xsy.entity.user.User;
import com.xsy.entity.video.Video;
import com.xsy.entity.video.VideoDetailVO;
import com.xsy.entity.video.VideoVO;
import com.xsy.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频(Video)表控制层
 *
 * @author makejava
 * @since 2021-08-06 16:38:14
 */
@RestController
public class VideoController {
    /**
     * 服务对象
     */
    @Resource
    private VideoService videoService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public Video selectOne(Integer id) {
        return this.videoService.queryById(id);
    }


    @GetMapping("/videos")
    public CommonResult<Map> videos(@RequestBody Map<String,Integer> params){
        int page=params.get("page");
        int pageSize=params.get("pageSize");

        int start=(page-1)*pageSize;
        List<VideoVO> videoVOS = videoService.queryAllByLimit(start, pageSize);
        int total=videoService.queryAllCount();
        Map<String,Object> result=new HashMap<>();
        //处理点赞未实现

        result.put("total",total);
        result.put("videos",videoVOS);
        return new CommonResult<Map>(200,"视频列表",result);
    }


    @GetMapping("/byCategory")
    public CommonResult<Map> videosByCaterory(@RequestBody Map<String,Integer> params){
        int page=params.get("page");
        int pageSize=params.get("pageSize");
        int category_id=params.get("category_id");

        int start=(page-1)*pageSize;


        List<VideoVO> videoVOS=videoService.queryByCategory(start,pageSize,category_id);

        int total=videoService.queryByCategoryCount(category_id);


        Map<String,Object> result=new HashMap<>();
        //处理点赞未实现

        result.put("total",total);
        result.put("videos",videoVOS);


        return new CommonResult<Map>(200,"视频列表",result);

    }


    @GetMapping("/video/{id}")
    public CommonResult<VideoDetailVO> detail(@PathVariable("id") Integer id, String token){
        Date date=new Date();
        if (!ObjectUtils.isEmpty(token)){
            User user = (User) redisTemplate.opsForValue().get(RedisPrefix.TOKEN + token);
            System.out.println(user.getId());
            //查询有没有这条播放历史
            Played played=videoService.queryByUidAndVid(id,user.getId());

            if (ObjectUtils.isEmpty(played)){
                played=new Played();
                played.setVideoId(id);
                played.setUid(user.getId());
                played.setCreatedAt(date);
                System.out.println(played);
                videoService.insertPlayed(played);
            }
            else {
                played.setCreatedAt(date);
                videoService.updatePlayed(played);
            }


        }
        redisTemplate.opsForValue().increment(RedisPrefix.VIDEO_PLAYED_COUNT+id);
        VideoDetailVO videoDetailVO=videoService.queryDetailById(id,token);
        return new CommonResult<VideoDetailVO>(200,"视频详情",videoDetailVO);
    }




}
