package com.cloud.cloudtrigger;


import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@RestController
public class TriggerController {

    private static final String LOCAL_AGENT_URL = "https://155c-196-132-12-149.ngrok-free.app/run-job"; // for now

    @PostMapping("/trigger-job")
    public ResponseEntity<String> triggerJob(@RequestBody JobRequest job) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(job); // ✅ convert object to JSON

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LOCAL_AGENT_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return ResponseEntity.status(response.statusCode()).body(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Error: " + e.getMessage());
        }
    }
}
