package com.example.music.service;

import com.example.music.model.Playlist;
import com.example.music.model.Track;
import com.example.music.repository.PlaylistRepository;

public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void createPlaylist(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    public void addTrack(int playlistId, Track track) {
        Playlist playlist = playlistRepository.findById(playlistId);
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist not found: " + playlistId);
        }
        if (playlist.containsTrack(track)) {
            throw new IllegalStateException(
                    "Track \"" + track.getTitle() + "\" is already in playlist \"" + playlist.getName() + "\"");
        }
        playlist.addTrack(track);
    }
}
