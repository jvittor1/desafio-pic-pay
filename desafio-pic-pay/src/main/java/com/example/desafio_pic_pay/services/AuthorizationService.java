package com.example.desafio_pic_pay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthorizationService {

    @Autowired
    private RestTemplate restTemplate;

    public void authorize() throws Exception {
        String URL = "https://util.devi.tools/api/v2/authorize";
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(URL, Map.class);
            var data = response.getBody();
            if (data == null || !"success".equals(data.get("status"))) {
                throw new Exception("Authorization failed: Status not successful");
            }
            Map<String, Object> dataMap = (Map<String, Object>) data.get("data");
            if (dataMap == null || !Boolean.TRUE.equals(dataMap.get("authorization"))) {
                throw new Exception("Authorization failed: Unauthorized access");
            }
        } catch (Exception e) {
            throw new Exception("Authorization failed");
        }
    }

}


