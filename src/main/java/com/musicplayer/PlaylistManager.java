package com.musicplayer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

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
        Scanner scanner = new Scanner(System.in);

        // Add songs to the playlist
        System.out.println("Enter the names of the songs you want to add to the playlist. Type 'done' when finished.");
        while (true) {
            String song = scanner.nextLine();
            if (song.equalsIgnoreCase("done")) {
                break;
            }
            addSong(song);
        }

        // Print the playlist
        System.out.println(getPlaylist());

        // Start playing the first song
        if (playlist.getFirst() != null) {
            playSong(playlist.getFirst().getData());
        } else {
            System.out.println("Playlist is empty.");
        }

        // Start a thread for taking input
        new Thread(() -> {
            while (true) {
                System.out.println("Enter a command:");
                String input = scanner.nextLine();
                handleInput(input);
            }
        }).start();

    }

    public static void addSong(String song) {
        playlist.addNode(song);
    }

    public static void playSong(String song) {
    try {
        FileInputStream file = new FileInputStream(song);
        player = new AdvancedPlayer(file);
        isPlaying = true;

        // Start a thread for playing the song
        new Thread(() -> {
            try {
                player.setPlayBackListener(new PlaybackListener() {
                    @Override
                    public void playbackFinished(PlaybackEvent evt) {
                        // Song playback finished, play the next song
                        isPlaying = false;
                        playlist.playNextSong();
                        playSong(playlist.getCurrentSong());
                    }
                });

                player.play();
            } catch (JavaLayerException e) {
                e.printStackTrace();
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
    public static void playNextSong() {
        playlist.playNextSong();
    }

    public static void playPreviousSong() {
        playlist.playPreviousSong();
    }
}
