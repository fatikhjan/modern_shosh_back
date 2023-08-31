package it.shoshapartment.Entity.Service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class TelegramService {

    private static final String BOT_TOKEN = "6647582824:AAHydc-AIZA8ZPyUa3WIuIBmRgz5fU0IQSs";
    private static final String GROUP_CHAT_ID = "-993533793"; // Replace with your group's chat ID

    private final RestTemplate restTemplate;


    public void sendMessage(String message) {
        String apiUrl = "https://api.telegram.org/bot" + BOT_TOKEN + "/sendMessage";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"chat_id\":\"" + GROUP_CHAT_ID + "\",\"text\":\"" + message + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Message sent successfully");
        } else {
            System.err.println("Failed to send message. Status code: " + response.getStatusCodeValue());
        }
    }
}
