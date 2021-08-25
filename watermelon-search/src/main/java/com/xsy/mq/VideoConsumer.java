package com.xsy.mq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xsy.entity.video.Video;
import com.xsy.entity.video.VideoVO;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class VideoConsumer {

    @Autowired
    private RestHighLevelClient restHighLevelClient;



//    @RabbitListener(queuesToDeclare = @Queue("hello"))
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "myexchange",type = "fanout")
    ))
    public void receive(String message) throws IOException {

            VideoVO videoVO=new ObjectMapper().readValue(message, VideoVO.class);
            System.out.println("message: "+videoVO);

            IndexRequest indexRequest=new IndexRequest("video","video",videoVO.getId().toString());

            indexRequest.source(message, XContentType.JSON);
            IndexResponse indexResponse=restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);




        }

    }
