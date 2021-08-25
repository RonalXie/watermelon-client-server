package com.xsy.utils;

import com.xsy.entity.video.Video;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-VIDEO")
public interface VideoClient {


    @PostMapping("/video")
    public void insertOne(@RequestBody Video video);

    @GetMapping("/fetchframe")
    public void fetchframe(@RequestParam String video,@RequestParam String cover);

}
