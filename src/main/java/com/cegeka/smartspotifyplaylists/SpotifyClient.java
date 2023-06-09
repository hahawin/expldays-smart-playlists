package com.cegeka.smartspotifyplaylists;

import com.cegeka.smartspotifyplaylists.domain.AccessToken;
import com.cegeka.smartspotifyplaylists.domain.Playlist;
import com.cegeka.smartspotifyplaylists.domain.PlaylistResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class SpotifyClient {

    @Value("${client_id}")
    private String clientId;
    @Value("${client_secret}")
    private String clientSecret;

    @Autowired
    private UserState userState;

    private final WebClient webClient;

    public SpotifyClient() {
        webClient = WebClient.builder().build();
    }

    public Mono<AccessToken> getAccessTokenWithAuthorizationCode(String code) {
        System.out.println(clientId);
        System.out.println(clientSecret);


      return webClient.post()
                .uri("https://accounts.spotify.com/api/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .headers(httpHeaders -> httpHeaders.setBasicAuth(clientId, clientSecret))
                .bodyValue(String.format("grant_type=authorization_code&code=%s&redirect_uri=http://localhost:8080/spotify-redirect", code))
                .retrieve()
                .bodyToMono(AccessToken.class);
    }

    public Mono<List<Playlist>> getPlaylists() {
        return webClient.get()
                .uri("https://api.spotify.com/v1/me/playlists")
                .headers(httpHeaders -> httpHeaders.setBearerAuth(userState.getAccessToken().accessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<PlaylistResponse>() {}
                ).map(PlaylistResponse::items);
    }
}
