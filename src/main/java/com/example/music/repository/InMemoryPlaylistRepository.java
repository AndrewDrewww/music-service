package com.example.music.repository;

import com.example.music.model.Playlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryPlaylistRepository implements PlaylistRepository {

    private final Map<Integer, Playlist> playlists = new HashMap<>();

    @Override
    public void save(Playlist playlist) {
        playlists.put(playlist.getId(), playlist);
    }

    @Override
    public Playlist findById(int id) {
        return playlists.get(id);
    }

    @Override
    public List<Playlist> findAll() {
        return new ArrayList<>(playlists.values());
    }
}
