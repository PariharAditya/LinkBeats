package com.musicplayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Player {
    private static AdvancedPlayer player;
    private static boolean isPlaying = false;
    private static int currentSongIndex = 0;
    private static String[] playlist = {
        "Akatsuki.mp3",
        "NeonBlade.mp3",
        "Itachi.mp3"
        // Add more songs to your playlist as needed
    };

    public static void main(String[] args) {
        playSong(playlist[currentSongIndex]);

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

    private static void handleInput(String input) {
        if (isPlaying) {
            System.out.println("Stopping current song...");
            stopSong();
        }

        if (input.equalsIgnoreCase("N")) {
            // Play next song
            currentSongIndex = (currentSongIndex + 1) % playlist.length;
            System.out.println("Playing next song: " + playlist[currentSongIndex]);
            playSong(playlist[currentSongIndex]);
        } else if (input.equalsIgnoreCase("P")) {
            // Play previous song
            currentSongIndex = (currentSongIndex - 1 + playlist.length) % playlist.length;
            System.out.println("Playing previous song: " + playlist[currentSongIndex]);
            playSong(playlist[currentSongIndex]);
        } else if (input.equalsIgnoreCase("X")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
        // You can add more commands as needed
    }
}
