package com.cegeka.smartspotifyplaylists.domain;

import java.util.List;

public record Playlist(String name, List<Image> images, String id) {

}
