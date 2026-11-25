package com.example.music.repository;

import com.example.music.model.Playlist;
import com.example.music.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InMemoryPlaylistRepositoryTest {

    @Test
    void findById_returnsNull_whenPlaylistWasNeverSaved() {
        PlaylistRepository repository = new InMemoryPlaylistRepository();

        assertNull(repository.findById(42));
    }

    @Test
    void save_thenFindById_returnsTheSamePlaylist() {
        PlaylistRepository repository = new InMemoryPlaylistRepository();
        Playlist playlist = new Playlist(1, "Плейлист", new User(1, "Анна"));

        repository.save(playlist);

        assertEquals(playlist, repository.findById(1));
    }

    @Test
    void findAll_returnsAllSavedPlaylists() {
        PlaylistRepository repository = new InMemoryPlaylistRepository();
        repository.save(new Playlist(1, "Утро", new User(1, "Анна")));
        repository.save(new Playlist(2, "Вечер", new User(1, "Анна")));

        assertEquals(2, repository.findAll().size());
        assertTrue(repository.findAll().stream().anyMatch(p -> p.getName().equals("Утро")));
        assertTrue(repository.findAll().stream().anyMatch(p -> p.getName().equals("Вечер")));
    }
}
