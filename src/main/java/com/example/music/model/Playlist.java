package com.example.music.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist {

    private final int id;
    private final String name;
    private final User owner;
    private final List<Track> tracks = new ArrayList<>();

    public Playlist(int id, String name, User owner) {
        this.id = id;
        this.name = name;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    public boolean containsTrack(Track track) {
        return tracks.contains(track);
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }
}
