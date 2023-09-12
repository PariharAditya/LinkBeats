package com.musicplayer;

import com.musicplayer.MusicController.*;
import java.util.LinkedList;
import java.util.List;

public class PlaylistManager {
    private List<String> playlist;

    public PlaylistManager() {
        playlist = new LinkedList<>();
    }

    public void addSong(String song) {
        playlist.add(song);
    }

    public void removeSong(String song) {
        playlist.remove(song);
    }

    public void playPlaylist() {
        for (String song : playlist) {
            try {
                System.out.println("Now playing: " + song);
                Player.sPlayer(song);
            } catch (Exception e) {
                System.err.println("Error playing " + song + ": " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        PlaylistManager manager = new PlaylistManager();

        // Add songs to the playlist
        manager.addSong("Akatsuki.mp3");
        // manager.addSong("song2.mp3");
        // manager.addSong("song3.mp3");

        // Remove a song from the playlist (optional)
        // manager.removeSong("song2.mp3");

        // Play the playlist
        manager.playPlaylist();
    }
}
