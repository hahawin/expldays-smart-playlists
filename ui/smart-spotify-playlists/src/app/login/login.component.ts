import { Component } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {

  login() {
    console.log('logging in');
    window.open( "https://accounts.spotify.com:443/authorize?" +
    "client_id=4d9006ad74f7467fa92d941c8285fe7b&" +
    "response_type=code&" +
    "scope=playlist-read-private playlist-modify-private playlist-modify-public&" + 
    "redirect_uri=http://localhost:8080/spotify-redirect");
  }

}
