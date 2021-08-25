package com.xsy.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Configuration
public class EsConfig extends AbstractElasticsearchConfiguration {
    @Override

    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration=ClientConfiguration.builder()
                .connectedTo("222.19.236.131:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
