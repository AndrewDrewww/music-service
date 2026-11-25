package com.example.music.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlaylistTest {

    @Test
    void containsTrack_returnsFalse_whenPlaylistIsEmpty() {
        Playlist playlist = new Playlist(1, "Пустой", new User(1, "Анна"));
        Track track = new Track(1, "Bohemian Rhapsody", "Queen");

        assertFalse(playlist.containsTrack(track));
    }

    @Test
    void containsTrack_returnsTrue_afterTrackWasAdded() {
        Playlist playlist = new Playlist(1, "Плейлист", new User(1, "Анна"));
        Track track = new Track(1, "Bohemian Rhapsody", "Queen");

        playlist.addTrack(track);

        assertTrue(playlist.containsTrack(track));
    }

    @Test
    void getTracks_returnsUnmodifiableList() {
        Playlist playlist = new Playlist(1, "Плейлист", new User(1, "Анна"));
        Track track = new Track(1, "Bohemian Rhapsody", "Queen");
        playlist.addTrack(track);

        assertThrows(UnsupportedOperationException.class, () -> playlist.getTracks().add(track));
    }

    @Test
    void getTracks_reflectsNumberOfAddedTracks() {
        Playlist playlist = new Playlist(1, "Плейлист", new User(1, "Анна"));
        playlist.addTrack(new Track(1, "Bohemian Rhapsody", "Queen"));
        playlist.addTrack(new Track(2, "Yesterday", "The Beatles"));

        assertEquals(2, playlist.getTracks().size());
    }
}
