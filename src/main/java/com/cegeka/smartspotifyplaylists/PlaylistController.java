package com.cegeka.smartspotifyplaylists;

import com.cegeka.smartspotifyplaylists.domain.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private UserState userState;

    @Autowired
    private SpotifyClient spotifyClient;

    @GetMapping()
    public Mono<List<Playlist>> getPlaylists() {
        return spotifyClient.getPlaylists();
    }
}
