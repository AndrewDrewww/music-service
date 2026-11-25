package com.example.music.service;

import com.example.music.model.Playlist;
import com.example.music.model.Track;
import com.example.music.model.User;
import com.example.music.repository.InMemoryPlaylistRepository;
import com.example.music.repository.PlaylistRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Индивидуальное задание ЛР №2 (вариант 11 — "Музыкальный сервис"):
 * дополнительный набор тестов для PlaylistService, запускаемый через Maven (mvn test).
 */
class PlaylistServiceAdditionalTest {

    private PlaylistService newService() {
        PlaylistRepository repository = new InMemoryPlaylistRepository();
        return new PlaylistService(repository);
    }

    @Test
    void createPlaylist_savesPlaylistIntoRepository() {
        // Arrange
        PlaylistRepository repository = new InMemoryPlaylistRepository();
        PlaylistService service = new PlaylistService(repository);
        User owner = new User(2, "Игорь");
        Playlist playlist = new Playlist(2, "Плейлист для тренировки", owner);

        // Act
        service.createPlaylist(playlist);

        // Assert
        assertNotNull(repository.findById(2));
        assertEquals("Плейлист для тренировки", repository.findById(2).getName());
    }

    @Test
    void addTrack_allowsSeveralDifferentTracksInSamePlaylist() {
        // Arrange
        PlaylistService service = newService();
        User owner = new User(3, "Мария");
        Playlist playlist = new Playlist(3, "Разное", owner);
        service.createPlaylist(playlist);

        Track track1 = new Track(1, "Bohemian Rhapsody", "Queen");
        Track track2 = new Track(2, "Yesterday", "The Beatles");
        Track track3 = new Track(3, "Imagine", "John Lennon");

        // Act
        service.addTrack(playlist.getId(), track1);
        service.addTrack(playlist.getId(), track2);
        service.addTrack(playlist.getId(), track3);

        // Assert
        assertEquals(3, playlist.getTracks().size());
        assertTrue(playlist.getTracks().containsAll(java.util.List.of(track1, track2, track3)));
    }

    @Test
    void addTrack_allowsSameTrackInDifferentPlaylists() {
        // Arrange
        PlaylistService service = newService();
        User owner = new User(4, "Олег");
        Playlist playlistA = new Playlist(10, "Утро", owner);
        Playlist playlistB = new Playlist(11, "Вечер", owner);
        service.createPlaylist(playlistA);
        service.createPlaylist(playlistB);
        Track track = new Track(5, "Hotel California", "Eagles");

        // Act
        service.addTrack(playlistA.getId(), track);
        service.addTrack(playlistB.getId(), track);

        // Assert — ограничение действует только в рамках одного плейлиста
        assertTrue(playlistA.containsTrack(track));
        assertTrue(playlistB.containsTrack(track));
    }
}
