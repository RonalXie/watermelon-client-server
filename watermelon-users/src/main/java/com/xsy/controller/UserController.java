package com.xsy.controller;


import com.sun.corba.se.impl.oa.toa.TOA;
import com.xsy.entity.CommonResult;
import com.xsy.entity.user.Favorite;
import com.xsy.entity.RedisPrefix;
import com.xsy.entity.user.User;
import com.xsy.entity.video.VideoVO;
import com.xsy.entity.vo.BaseUser;
import com.xsy.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户(User)表控制层
 *
 * @author makejava
 * @since 2021-08-06 16:37:10
 */
@RestController
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/selectOne")
    public User selectOne(Integer id){
        return userService.queryById(id);
    }


    @PostMapping("/tokens")
    public CommonResult<Map> login(@RequestBody Map<String, String> param, HttpSession httpSession) {
        String phone = param.get("phone");
        String captcha = param.get("captcha");
        User userDB = userService.queryByPhone(phone);
        if (ObjectUtils.isEmpty(userDB)) {
            User user = new User();
            user.setName("罗纳尔多");
            user.setPhone(phone);
            Date date = new Date();
            user.setCreatedAt(date);
            user.setUpdatedAt(date);
            user.setIntro("");
            user.setAvatar("http://p4.music.126.net/UCQa5eEZWxSKbnBwxz_Flg==/109951166002279099.jpg?param=200y200");
            user.setPhoneLinked(1);
            user.setWechatLinked(0);
            user.setFollowingCount(0);
            user.setFollowersCount(0);
            userDB = userService.insert(user);
        }
        String token = httpSession.getId();
        redisTemplate.opsForValue().set(RedisPrefix.TOKEN + token, userDB, 7, TimeUnit.DAYS);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        return new CommonResult<Map>(200, "登录成功!", data);
    }


    @GetMapping("/user")
    public CommonResult<BaseUser> userInfo(String token) {

        User user = (User) redisTemplate.opsForValue().get(RedisPrefix.TOKEN + token);
        BaseUser baseUser = new BaseUser();
        BeanUtils.copyProperties(user, baseUser);

        return new CommonResult<BaseUser>(200, "用户信息", baseUser);

    }

    @DeleteMapping("/tokens")
    public void logout(String token){
        redisTemplate.delete(RedisPrefix.TOKEN+token);

    }

    @PatchMapping("user")
    public CommonResult<BaseUser> update(@RequestBody Map<String,String> params){

        String token=params.get("token");
        User oldUser= (User) getFromRedis(RedisPrefix.TOKEN+token);

        oldUser.setAvatar(params.get("avatar"));
        oldUser.setPhone(params.get("phone"));
        oldUser.setName(params.get("name"));
        oldUser.setIntro(params.get("intro"));

        redisTemplate.opsForValue().set(RedisPrefix.TOKEN+token,oldUser,7,TimeUnit.DAYS);
        userService.update(oldUser);
        BaseUser baseUser=new BaseUser();
        BeanUtils.copyProperties(oldUser,baseUser);
        return new CommonResult<BaseUser>(200,"修改成功!",baseUser);

    }
    @PutMapping("/liked/{video_id}")
    public void liked(@PathVariable("video_id") Integer video_id,String token){
        if (token==null) throw new RuntimeException("请登录");
        User user = (User) getFromRedis(RedisPrefix.TOKEN+token);
        if (user==null) throw new RuntimeException("非法访问");
        //记录当前视频点赞总数
        redisTemplate.opsForValue().increment(RedisPrefix.VIDEO_LiKED_COUNT+video_id);
        //记录当前用户是否对该视频进行点赞
        redisTemplate.opsForSet().add(RedisPrefix.USER_LIKED_ID+user.getId(),video_id);

    }

    @DeleteMapping("/liked/{video_id}")
    public void unliked(@PathVariable("video_id") Integer video_id,String token){
        //当前视频点赞次数减一
        redisTemplate.opsForValue().decrement(RedisPrefix.VIDEO_LiKED_COUNT+video_id);
        User user=(User)getFromRedis(RedisPrefix.TOKEN+token);
        redisTemplate.opsForSet().remove(RedisPrefix.USER_LIKED_ID+user.getId(),video_id);

    }

    @PutMapping("/favorite/{id}")
    public void favorite(@PathVariable("id") Integer video_id,String token){
        if (!ObjectUtils.isEmpty(token)){
            User user=(User) getFromRedis(RedisPrefix.TOKEN+token);
            Favorite favorite=userService.queryFavorite(user.getId(),video_id);
            if (ObjectUtils.isEmpty(favorite)){
                userService.insertFavorite(video_id,user.getId());
            }
        }
    }

    @GetMapping("/favorite")
    public boolean isFavorite(Integer id,Integer video_id){
        Favorite favorite=userService.queryFavorite(id,video_id);
        if (ObjectUtils.isEmpty(favorite)){
            return false;
        }
        return true;
    }

    @GetMapping("/favorites")
    public CommonResult<Map> favorites(Integer page,Integer pageSize,String token){

        User user= (User) getFromRedis(RedisPrefix.TOKEN+token);
        List<VideoVO> videoVOS=userService.queryFavorites(page,pageSize,user.getId());

        Map<String,Object> result=new HashMap<>();
        result.put("total",userService.queryFavoriteCount(user.getId()));
        result.put("data",videoVOS);

        return new CommonResult<Map>(200,"收藏列表",result);
    }

    @DeleteMapping("/favorite/{id}")
    public void deleteFavorite(@PathVariable("id") Integer video_id,String token){
        User user= (User) getFromRedis(RedisPrefix.TOKEN+token);


        userService.deleteFavorite(user.getId(),video_id);

    }

    @GetMapping("/played")
    public CommonResult<List> played(Integer page, Integer pageSize, String token){
        User user= (User) getFromRedis(RedisPrefix.TOKEN+ token);

        List<VideoVO> videoVOS=userService.queryPlayed(page,pageSize,user.getId());


        Map<String,Object> result=new HashMap<>();
        result.put("total",userService.queryPlayedCount(user.getId()));
        result.put("data",videoVOS);

        return new CommonResult<List>(200,"播放历史",videoVOS);
    }





    public Object getFromRedis(String token){
        return redisTemplate.opsForValue().get(token);
    }

}
