package com.musicplayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlaylistManager {
    private CircularDoublyLinkedList playlist;
    private AdvancedPlayer player;

    public PlaylistManager() {
        playlist = new CircularDoublyLinkedList();
    }

    public void addSong(String song) {
        playlist.addNode(song);
    }

    public void removeSong(String song) {
        playlist.removeNode(song);
    }

    public void playPlaylist() throws InterruptedException {
        CircularDoublyLinkedList.Node current = playlist.getFirst();

        while (true) {
            System.out.println("Now playing: " + current.getData());
            playSong(current.getData());

            // Move to the next song
            current = current.getNext();

            // You can exit the loop when you want
            // For example, break; if some condition is met
        }
    }

    private void playSong(String song) {
        try {
            FileInputStream file = new FileInputStream(song);
            player = new AdvancedPlayer(file);
            player.play();
        } catch (FileNotFoundException | JavaLayerException e) {
            System.err.println("Error playing " + song + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PlaylistManager manager = new PlaylistManager();

        // Add songs to the playlist
        // manager.addSong("Akatsuki.mp3");
        // manager.addSong("NeonBlade.mp3");
        // manager.addSong("itachi.mp3");

        // Play the playlist
        manager.playPlaylist();
    }
}
