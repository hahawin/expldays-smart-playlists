import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Playlist } from './domain/playlist';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  constructor(private http: HttpClient) { }

  getPlaylists() : Observable<Playlist[]> {
    return this.http.get<Playlist[]>('http://localhost:8080/playlists')
  }
}
