package com.cegeka.smartspotifyplaylists;

import com.cegeka.smartspotifyplaylists.domain.AccessToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class SmartSpotifyPlaylistsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartSpotifyPlaylistsApplication.class, args);

	}

}
