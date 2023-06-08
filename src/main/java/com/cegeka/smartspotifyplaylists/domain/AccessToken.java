package com.cegeka.smartspotifyplaylists.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessToken(@JsonProperty("access_token") String accessToken, @JsonProperty("token_type") String tokenType) {


}
