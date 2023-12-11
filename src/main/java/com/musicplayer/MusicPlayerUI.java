package com.musicplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicPlayerUI extends JFrame {
    // private static AdvancedPlayer player;
    private static boolean isPlaying = false;
    private static CircularDoublyLinkedList playlist = new CircularDoublyLinkedList();

    private JTextArea playlistTextArea;

    public MusicPlayerUI() {
        // Set up the main frame
        setTitle("Music Player");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create buttons
        JButton playButton = new JButton("Play");
        playButton.setName("Play");
        JButton stopButton = new JButton("Stop");
        stopButton.setName("Stop");
        JButton nextButton = new JButton("Next");
        nextButton.setName("Next");
        JButton prevButton = new JButton("Previous");
        prevButton.setName("Previous");
        JButton addSongsButton = new JButton("Add Songs");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSong();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSong();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playNextSong();
            }
        });

        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playPreviousSong();
            }
        });

        addSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSongs();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Create text area for displaying the playlist
        playlistTextArea = new JTextArea(15, 30);
        playlistTextArea.setEditable(false);        

        setLayout(new FlowLayout());
        add(playButton);
        add(stopButton);
        add(nextButton);
        add(prevButton);
        add(addSongsButton);
        add(exitButton);
        add(new JScrollPane(playlistTextArea), BorderLayout.CENTER);
    }

    private void playSong() {
        if (!playlist.isEmpty()) {
            PlaylistManager.playSong(playlist.getCurrentSong());
            isPlaying = true; // Set isPlaying to true when play button is clicked
        } else {
            System.out.println("Playlist is empty.");
        }
    }
    
    private void stopSong() {
        PlaylistManager.stopSong();
    }

    private void playNextSong() {
        PlaylistManager.playNextSong();
        updatePlaylistTextArea();
    }

    private void playPreviousSong() {
        PlaylistManager.playPreviousSong();
        updatePlaylistTextArea();
    }

    private void addSongs() {
        // Open a file dialog to choose multiple songs
        while (true) {
            String songName = JOptionPane.showInputDialog(this, "Enter the song name (Type 'done' to finish):");
    
            if (songName == null || songName.trim().equalsIgnoreCase("done")) {
                break;
            }
    
            // Check if the song name is not empty
            if (!songName.trim().isEmpty()) {
                // Add the song to the playlist
                PlaylistManager.addSong(songName.trim() + ".mp3");
            } else {
                System.out.println("No song was added.");
            }
        }
    
        // After adding songs, play the first song
        PlaylistManager.playSong(PlaylistManager.getFirst().getData());
    }
    

    private void updatePlaylistTextArea() {
        playlistTextArea.setText(PlaylistManager.getPlaylist());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusicPlayerUI().setVisible(true);
            }
        });
    }
}