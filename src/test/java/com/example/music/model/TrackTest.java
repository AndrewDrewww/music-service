package com.example.music.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TrackTest {

    @Test
    void equals_returnsTrue_forTracksWithSameId() {
        Track track1 = new Track(1, "Bohemian Rhapsody", "Queen");
        Track track2 = new Track(1, "Bohemian Rhapsody (Remastered)", "Queen");

        assertEquals(track1, track2);
    }

    @Test
    void equals_returnsFalse_forTracksWithDifferentId() {
        Track track1 = new Track(1, "Bohemian Rhapsody", "Queen");
        Track track2 = new Track(2, "Bohemian Rhapsody", "Queen");

        assertNotEquals(track1, track2);
    }

    @Test
    void hashCode_isSame_forTracksWithSameId() {
        Track track1 = new Track(1, "Bohemian Rhapsody", "Queen");
        Track track2 = new Track(1, "Другое название", "Другой исполнитель");

        assertEquals(track1.hashCode(), track2.hashCode());
    }
}
