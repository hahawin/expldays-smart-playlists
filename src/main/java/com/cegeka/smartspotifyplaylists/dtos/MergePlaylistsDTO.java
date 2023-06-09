package com.cegeka.smartspotifyplaylists.dtos;

import java.util.List;

public record MergePlaylistsDTO(List<String> idsPlaylistsToMerge, String titleMergedPlaylist) {

}
