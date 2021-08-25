package com.xsy.utils;

import com.xsy.entity.user.User;
import com.xsy.entity.video.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-USERS")
public interface UserClient {

    @GetMapping("/selectOne")
    public User selectOne(@RequestParam Integer id);
    @GetMapping("favorite")
    public boolean isFavorite(@RequestParam Integer id,@RequestParam Integer video_id);

    @PostMapping("/comments")
    public void PushComments(@RequestBody Comment comment);



}
