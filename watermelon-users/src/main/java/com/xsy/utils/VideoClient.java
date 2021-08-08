package com.xsy.utils;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("USER-VIDEO")
public interface VideoClient {

}
