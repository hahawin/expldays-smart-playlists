package com.cegeka.smartspotifyplaylists.domain;

import java.util.List;

public record InsertTrackTP(List<String> uris, int position) {
}
