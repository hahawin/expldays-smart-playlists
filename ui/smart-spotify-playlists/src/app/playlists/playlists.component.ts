import { Component } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Playlist } from '../domain/playlist';
import { PlaylistService } from '../playlist-service.service';

@Component({
  selector: 'app-playlists',
  templateUrl: './playlists.component.html',
  styleUrls: ['./playlists.component.css']
})
export class PlaylistsComponent {
mergePlaylists(playlists: Playlist[]) {
throw new Error('Method not implemented.');
}

  playLists: Observable<Playlist[]> = new Observable(); 
  selectedPlayLists = [];
  compareFunction = (o1: any, o2: any)=> o1.id===o2.id;

  constructor( private playlistService : PlaylistService) {}

  getPlaylists() {
    console.log('playlists')
    this.playLists = this.playlistService.getPlaylists()
  }


}
