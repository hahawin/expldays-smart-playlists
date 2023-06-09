package com.cegeka.smartspotifyplaylists;

import com.cegeka.smartspotifyplaylists.domain.AccessToken;
import org.springframework.stereotype.Component;

@Component
public class UserState {

    private AccessToken accessToken;

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
