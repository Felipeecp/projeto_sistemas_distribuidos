package com.ufma.wmo.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufma.wmo.application.exceptions.BusinessExceptions;
import com.ufma.wmo.domain.dto.SensorDetail;
import com.ufma.wmo.domain.rawData.RowCities;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendDataImpl implements SendData{

    @Value("${urlApplication}")
    private String host;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public void sendData(SensorDetail sensorDetail) {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(100);

        CloseableHttpClient client = HttpClients.custom()
                .evictExpiredConnections()
                .setConnectionManager(connManager)
                .build();
        try {
            String entity = mapper.writeValueAsString(sensorDetail);
            HttpPost httpPost = new HttpPost(host);
            httpPost.setEntity(new StringEntity(entity, "UTF-8"));

            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("charset", "UTF-8");

            client.execute(httpPost);
            client.close();
        } catch (IOException e) {
            throw new BusinessExceptions(e.getMessage());
        }

        connManager.close();

    }

}
