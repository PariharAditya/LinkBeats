package com.musicplayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import com.musicplayer.CircularDoublyLinkedList.Node;

public class PlaylistManager {
    private static AdvancedPlayer player;
    private static boolean isPlaying = false;
    private static CircularDoublyLinkedList playlist = new CircularDoublyLinkedList();

    public static void main(String[] args) {
        startMusicPlayer();
    }

    public static void startMusicPlayer() {
        // Add songs to the playlist
        playlist.addNode("Akatsuki.mp3");
        playlist.addNode("NeonBlade.mp3");
        playlist.addNode("Itachi.mp3");
        // Add more songs to your playlist as needed

        // Print the playlist
        System.out.println(getPlaylist());

        // Start playing the first song
        playSong(playlist.getFirst().getData());

        // Start a thread for taking input
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                handleInput(input);
            }
        }).start();
    }

    public static void playSong(String song) {
        try {
            FileInputStream file = new FileInputStream(song);
            player = new AdvancedPlayer(file);
            isPlaying = true;

            // Start a thread for playing the song
            new Thread(() -> {
                // isPlaying = true;
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } finally {
                    isPlaying = false;
                }
            }).start();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public static void stopSong() {
        if (player != null) {
            player.close();
        }
    }

    public static void handleInput(String input) {
        if (isPlaying) {
            System.out.println("Stopping current song...");
            stopSong();
        }

        if (input.equalsIgnoreCase("N")) {
            // Play next song
            playlist.playNextSong();
            System.out.println(playlist.getCurrentSong());
            playSong(playlist.getCurrentSong());
        } else if (input.equalsIgnoreCase("P")) {
            // Play previous song
            playlist.playPreviousSong();
            System.out.println(playlist.getCurrentSong());
            playSong(playlist.getCurrentSong());
        } else if (input.equalsIgnoreCase("X")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }

    public static String getPlaylist() {
        if (playlist.isEmpty()) {
            return "Playlist is empty";
        } else {
            StringBuilder sb = new StringBuilder();
            Node current = playlist.getFirst();
            do {
                sb.append(current.getData()).append("\n");
                current = current.getNext();
            } while (current != playlist.getFirst());
            return sb.toString();
        }
    }

    public static Node getFirst() {
        return playlist.getFirst();
    }
    


    
}
