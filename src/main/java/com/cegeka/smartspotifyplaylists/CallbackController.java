package com.cegeka.smartspotifyplaylists;

import com.cegeka.smartspotifyplaylists.domain.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/spotify-redirect")
public class CallbackController {

    @Autowired
    private UserState userState;

    @Autowired
    private SpotifyClient spotifyClient;

    @GetMapping()
    public Mono<String> getEmployeeById(@RequestParam(required = false) String code, @RequestParam(required = false) String state) {
        System.out.println("Code: " + code);
        System.out.println("State: " + state);

        WebClient webClient = WebClient.create();

        return spotifyClient.getAccessTokenWithAuthorizationCode(code)
                .doOnNext(accessToken ->
                        webClient.get()
                                .uri("https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb")
                                .header ("Authorization", String.format("Bearer %s", accessToken.accessToken()))
                                .retrieve()
                                .bodyToMono(String.class)
                                .subscribe(System.out::printf)
                        )
                .doOnNext(accessToken -> userState.setAccessToken(accessToken))
                .map(accessToken -> String.format("{\"code\":\"%s\", \"state\":\"%s\", \"token\": \"%s\"}", code, state, accessToken));

//        return Mono.just(String.format("{\"code\":\"%s\", \"state\":\"%s\", \"token\": \"%s\"}", code, state, token));
    }
}
