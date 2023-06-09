package com.cegeka.smartspotifyplaylists;

import com.cegeka.smartspotifyplaylists.domain.Playlist;
import com.cegeka.smartspotifyplaylists.dtos.MergePlaylistsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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

    @PostMapping()
    public Mono<ResponseEntity<Void>> mergePlaylists(@RequestBody MergePlaylistsDTO mergePlaylistsDTO) {

        Mono<List<String>> monos = mergePlaylistsDTO.idsPlaylistsToMerge()
                .stream()
                .map(id -> spotifyClient.getTracks(id))
                .reduce((listMono, listMono2) -> listMono.zipWith(listMono2, (strings, strings2) -> {
                    List<String> result = new ArrayList<>();
                    result.addAll(strings);
                    result.addAll(strings2);
                    return result;
                })).orElseThrow();

        Mono<ResponseEntity<Void>> responseEntityMono = spotifyClient.createPlayList(mergePlaylistsDTO.titleMergedPlaylist())
                .flatMap(playlist -> monos.flatMap(strings -> spotifyClient.addItemsToPlayList(playlist.id(), strings)));


        return responseEntityMono;
    }
}
