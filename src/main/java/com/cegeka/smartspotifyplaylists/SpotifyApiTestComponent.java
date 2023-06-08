package com.cegeka.smartspotifyplaylists;

import com.cegeka.smartspotifyplaylists.domain.AccessToken;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SpotifyApiTestComponent {

    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;

    private final WebClient webClient;

    public SpotifyApiTestComponent() {
        webClient = WebClient.builder().build();
    }

    @PostConstruct
    public void printProperties() {
        System.out.println(clientId);
        System.out.println(clientSecret);


        AccessToken token = webClient.post()
                .uri("https://accounts.spotify.com/api/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(String.format("grant_type=client_credentials&client_id=%s&client_secret=%s", clientId, clientSecret))
                .retrieve()
                .bodyToMono(AccessToken.class)
                .block();

        System.out.printf(token.toString());

        String response = webClient.get()
                .uri("https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb")
                .header("Authorization", String.format("Bearer %s", token.accessToken()))
                .retrieve()
                .bodyToMono(String.class)
                .block();

        System.out.printf(response);
    }
}
