package com.example.music.service;

import com.example.music.model.Playlist;
import com.example.music.model.Track;
import com.example.music.model.User;
import com.example.music.repository.InMemoryPlaylistRepository;
import com.example.music.repository.PlaylistRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlaylistServiceTest {

    private Playlist newPlaylist() {
        User owner = new User(1, "Анна");
        return new Playlist(1, "Вечерний плейлист", owner);
    }

    private PlaylistService serviceWith(Playlist... playlists) {
        PlaylistRepository repository = new InMemoryPlaylistRepository();
        for (Playlist playlist : playlists) {
            repository.save(playlist);
        }
        return new PlaylistService(repository);
    }

    @Test
    void addTrack_addsTrackToPlaylist_whenTrackNotYetPresent() {
        // Arrange
        Playlist playlist = newPlaylist();
        PlaylistService service = serviceWith(playlist);
        Track track = new Track(1, "Bohemian Rhapsody", "Queen");

        // Act
        service.addTrack(playlist.getId(), track);

        // Assert
        assertEquals(1, playlist.getTracks().size());
        assertTrue(playlist.getTracks().contains(track));
    }

    @Test
    void addTrack_throwsIllegalArgumentException_whenPlaylistNotFound() {
        // Arrange
        PlaylistService service = serviceWith();
        Track track = new Track(1, "Bohemian Rhapsody", "Queen");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.addTrack(99, track));
    }

    @Test
    void addTrack_throwsIllegalStateException_whenTrackAlreadyInPlaylist() {
        // Arrange
        Playlist playlist = newPlaylist();
        PlaylistService service = serviceWith(playlist);
        Track track = new Track(1, "Bohemian Rhapsody", "Queen");
        service.addTrack(playlist.getId(), track);

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> service.addTrack(playlist.getId(), track));
        assertEquals(1, playlist.getTracks().size());
    }

    @Test
    void addTrack_preservesOrderOfAddedTracks() {
        // Arrange
        Playlist playlist = newPlaylist();
        PlaylistService service = serviceWith(playlist);
        Track first = new Track(1, "Bohemian Rhapsody", "Queen");
        Track second = new Track(2, "Yesterday", "The Beatles");
        Track third = new Track(3, "Imagine", "John Lennon");

        // Act
        service.addTrack(playlist.getId(), first);
        service.addTrack(playlist.getId(), second);
        service.addTrack(playlist.getId(), third);

        // Assert — порядок треков в плейлисте совпадает с порядком добавления
        assertEquals(first, playlist.getTracks().get(0));
        assertEquals(second, playlist.getTracks().get(1));
        assertEquals(third, playlist.getTracks().get(2));
    }
}
