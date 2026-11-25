package com.example.music;

import com.example.music.model.Playlist;
import com.example.music.model.Track;
import com.example.music.model.User;
import com.example.music.repository.InMemoryPlaylistRepository;
import com.example.music.repository.PlaylistRepository;
import com.example.music.service.PlaylistService;

public class Application {

    public static void main(String[] args) {
        PlaylistRepository playlistRepository = new InMemoryPlaylistRepository();
        PlaylistService playlistService = new PlaylistService(playlistRepository);

        User user = new User(1, "Анна");
        Playlist playlist = new Playlist(1, "Вечерний плейлист", user);
        playlistService.createPlaylist(playlist);

        Track track1 = new Track(1, "Bohemian Rhapsody", "Queen");
        Track track2 = new Track(2, "Yesterday", "The Beatles");

        playlistService.addTrack(playlist.getId(), track1);
        System.out.println("Добавлен трек \"" + track1.getTitle() + "\" в плейлист \"" + playlist.getName() + "\"");

        playlistService.addTrack(playlist.getId(), track2);
        System.out.println("Добавлен трек \"" + track2.getTitle() + "\" в плейлист \"" + playlist.getName() + "\"");

        try {
            playlistService.addTrack(playlist.getId(), track1);
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("Треки в плейлисте \"" + playlist.getName() + "\":");
        for (Track track : playlist.getTracks()) {
            System.out.println(" - " + track.getArtist() + " - " + track.getTitle());
        }
    }
}
