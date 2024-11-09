package com.example.desafio_pic_pay.services;

import com.example.desafio_pic_pay.dtos.NotificationDTO;
import com.example.desafio_pic_pay.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationDTO = new NotificationDTO(email, message);

        String URL = "https://util.devi.tools/api/v1/notify";
        ResponseEntity<String> response;

        try {
            response = restTemplate.postForEntity(URL, notificationDTO, String.class);
        } catch (Exception e) {
            throw new Exception("Error sending notification: Unable to reach notification service");
        }

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception("Error sending notification: Notification service returned an error");
        }
    }


}
