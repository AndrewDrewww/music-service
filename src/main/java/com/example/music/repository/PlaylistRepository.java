package com.example.music.repository;

import com.example.music.model.Playlist;

import java.util.List;

public interface PlaylistRepository {

    void save(Playlist playlist);

    Playlist findById(int id);

    List<Playlist> findAll();
}
